package bibarsov.telegram.bots.client.service;

import bibarsov.telegram.bots.client.dto.GetUpdateResponse;
import bibarsov.telegram.bots.client.dto.Update;
import bibarsov.telegram.bots.client.serialization.JsonHelper;
import bibarsov.telegram.bots.client.service.handler.Handler;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkNotNull;

@ParametersAreNonnullByDefault
public class UpdatePollerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdatePollerService.class);

    private static final String TG_UPD_URL = "https://api.telegram.org/bot%s/getUpdates?offset=%d&timeout=1";
    private static final long PAUSE_ON_ERROR_SECONDS = 3L;

    private final OkHttpClient httpClient = new OkHttpClient();

    private final JsonHelper jsonHelper;
    private final Dispatcher<? extends Enum<?>> dispatcher;
    private final String botApiKey;

    public <T extends Enum<T>> UpdatePollerService(
            String botApiKey,
            int workersThreadCount,
            Class<T> enumClass,
            Handler... handlers
    ) {
        this.botApiKey = botApiKey;
        this.dispatcher = new SimpleDispatcher<T>(
                workersThreadCount,
                handlers,
                enumClass
        );
        this.jsonHelper = new JsonHelper();
    }

    public UpdatePollerService(
            JsonHelper jsonHelper,
            Dispatcher<? extends Enum> dispatcher,
            String botApiKey
    ) {
        this.jsonHelper = jsonHelper;
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
                    GetUpdateResponse apiResponse = jsonHelper.deserialize(body.string(), GetUpdateResponse.class);
                    if (apiResponse.ok && checkNotNull(apiResponse.result).size() > 0) {
                        for (Update update : apiResponse.result) {
                            currentOffset = update.updateId + 1;
                            LOGGER.info("http >> updateId#" + currentOffset);
                            dispatcher.dispatch(update);
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
