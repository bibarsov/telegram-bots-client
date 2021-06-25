package ru.bibarsov.telegram.bots.client.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bibarsov.telegram.bots.client.dto.GetUpdateResponse;
import ru.bibarsov.telegram.bots.client.dto.Update;
import ru.bibarsov.telegram.bots.client.serialization.JsonHelper;
import ru.bibarsov.telegram.bots.client.service.handler.CommandHandler;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkNotNull;

@ParametersAreNonnullByDefault
public class UpdatePollerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdatePollerService.class);

    private static final String TG_UPD_URL = "https://api.telegram.org/bot%s/getUpdates?offset=%d&timeout=1";
    private static final long PAUSE_ON_ERROR_SECONDS = 3L;

    private final OkHttpClient httpClient = new OkHttpClient();

    private final JsonHelper jsonHelper;
    private final Dispatcher dispatcher;
    private final String botApiKey;

    public <T extends Enum<T>> UpdatePollerService(
        String botApiKey,
        int workersThreadCount,
        List<CommandHandler<T>> handlers,
        CommandHandler<T> defaultHandler,
        Class<T> enumClass
    ) {
        this.botApiKey = botApiKey;
        this.dispatcher = new BasicDispatcher<T>(
            workersThreadCount,
            handlers,
            defaultHandler,
            enumClass
        );
        this.jsonHelper = new JsonHelper();
    }

    public UpdatePollerService(
        JsonHelper jsonHelper,
        Dispatcher dispatcher,
        String botApiKey
    ) {
        this.jsonHelper = jsonHelper;
        this.dispatcher = dispatcher;
        this.botApiKey = botApiKey;
    }

    public UpdatePollerService(
        Dispatcher dispatcher,
        String botApiKey
    ) {
        this.jsonHelper = new JsonHelper();
        this.dispatcher = dispatcher;
        this.botApiKey = botApiKey;
    }

    public void doJob() {
        long currentOffset = -1;
        while (!Thread.interrupted()) {
            Request request = new Request.Builder()
                .url(String.format(TG_UPD_URL, botApiKey, currentOffset))
                .build();
            try (
                Response response = httpClient.newCall(request).execute();
                ResponseBody body = response.body()
            ) {
                if (response.isSuccessful() && body != null) {
                    String string = body.string();
                    LOGGER.debug("Got update: {}", string);
                    GetUpdateResponse apiResponse = jsonHelper.deserialize(string, GetUpdateResponse.class);
                    if (apiResponse.ok && checkNotNull(apiResponse.result).size() > 0) {
                        for (Update update : apiResponse.result) {
                            dispatcher.dispatch(update);
                            currentOffset = update.updateId + 1;
                        }
                    }
                }
            } catch (Throwable e) {
                LOGGER.error("Long polling error", e);
                try {
                    TimeUnit.SECONDS.sleep(PAUSE_ON_ERROR_SECONDS);
                } catch (InterruptedException ignored) {
                    //ignore
                }
            }
        }
    }
}
