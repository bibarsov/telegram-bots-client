package bibarsov.telegram.bots.client.service;

import bibarsov.telegram.bots.client.dto.EditMessageTextRequest;
import bibarsov.telegram.bots.client.dto.SendMessageRequest;
import bibarsov.telegram.bots.client.dto.SendMessageResponse;
import bibarsov.telegram.bots.client.serialization.JsonHelper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@ParametersAreNonnullByDefault
public class MessageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json");
    private static final String TG_SEND_MESSAGE_URL = "https://api.telegram.org/bot%s/sendMessage";
    private static final String TG_EDIT_MESSAGE_URL = "https://api.telegram.org/bot%s/editMessageText";
    private static final String TG_DELETE_MESSAGE_URL = "https://api.telegram.org/bot%s/deleteMessage?chat_id=%s&message_id=%s";

    private final ExecutorService executors = Executors.newCachedThreadPool();
    private final OkHttpClient httpClient = new OkHttpClient();

    private final JsonHelper jsonHelper;
    private final String apiKey;

    public MessageService(JsonHelper jsonHelper, String apiKey) {
        this.jsonHelper = jsonHelper;
        this.apiKey = apiKey;
    }

    public void scheduleMessage(long id, String text) {
        scheduleMessage(new SendMessageRequest(id, text, null, null, null));
    }

    public void scheduleMessage(SendMessageRequest request) {
        scheduleMessage(request, (sendMessageResponse) -> {
        });
    }

    public void scheduleMessage(SendMessageRequest request, Consumer<SendMessageResponse> consumer) {
        executors.submit(() -> consumer.accept(this.send(request)));
    }

    @Nullable
    public SendMessageResponse send(long userId, String text) {
        return send(new SendMessageRequest(userId, text, null, null, null));
    }

    @Nullable
    public SendMessageResponse send(long userId, String text, String parseMode) {
        return send(new SendMessageRequest(userId, text, parseMode, null, null));
    }

    @Nullable
    public SendMessageResponse send(SendMessageRequest sendMessageRequest) {
        Response response = null;
        try {
            String json = jsonHelper.serialize(sendMessageRequest);

            Request request = new Request.Builder()
                    .url(String.format(TG_SEND_MESSAGE_URL, apiKey))
                    .post(RequestBody.create(JSON_MEDIA_TYPE, json))
                    .build();

            response = httpClient.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                return jsonHelper.deserialize(response.body().string(), SendMessageResponse.class);
            }
        } catch (Throwable e) {
            LOGGER.error("Message sending failed. ", e);
        } finally {
            if (response != null && response.body() != null) {
                response.close();
            }
        }
        return null;
    }

    @Nullable
    public Boolean edit(EditMessageTextRequest editMessageTextRequest) {
        Response response = null;
        try {
            String json = jsonHelper.serialize(editMessageTextRequest);

            Request request = new Request.Builder()
                    .url(String.format(TG_EDIT_MESSAGE_URL, apiKey))
                    .post(RequestBody.create(JSON_MEDIA_TYPE, json))
                    .build();
            response = httpClient.newCall(request).execute();
            ResponseBody body = response.body();
            if (response.isSuccessful() && body != null) {
                JsonParser parser = new JsonParser();
                JsonObject o = parser.parse(body.string()).getAsJsonObject();
                return o.get("ok").getAsBoolean();
            }
            return false;
        } catch (Throwable e) {
            LOGGER.error("Message sending failed. ", e);
        } finally {
            if (response != null && response.body() != null) {
                response.close();
            }
        }
        return null;
    }

    public void deleteMessage(long chatId, long messageId) {
        Request request = new Request.Builder()
                .url(String.format(TG_DELETE_MESSAGE_URL, apiKey, chatId, messageId))
                .build();
        //noinspection EmptyTryBlock
        try (Response ignored = httpClient.newCall(request).execute()) {
            //nothing is needed
        } catch (Throwable e) {
            LOGGER.error("Message deleting failed. ", e);
        }
    }
}
