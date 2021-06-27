package ru.bibarsov.telegram.bots.client.repository.client;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bibarsov.telegram.bots.client.dto.*;
import ru.bibarsov.telegram.bots.client.serialization.JsonHelper;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

@ParametersAreNonnullByDefault
public class TelegramBotApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramBotApi.class);

    @ParametersAreNonnullByDefault
    private static class RequestParamBuilder {

        private final Map<String, String> params = new HashMap<>();

        public static RequestParamBuilder builder() {
            return new RequestParamBuilder();
        }

        public RequestParamBuilder addParam(String name, Object value) {
            params.put(name, String.valueOf(value));
            return this;
        }

        public String build() {
            return params.entrySet().stream()
                .map(e -> String.format("%s=%s", e.getKey(), e.getValue()))
                .collect(Collectors.joining("&"));
        }
    }

    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json");

    private final JsonHelper jsonHelper;
    private final String baseUri;
    private final OkHttpClient httpClient;

    public TelegramBotApi(JsonHelper jsonHelper, String botApiKey) {
        this.jsonHelper = jsonHelper;
        this.baseUri = String.format("https://api.telegram.org/bot%s", botApiKey);
        this.httpClient = new OkHttpClient.Builder().hostnameVerifier((hostname, session) -> true).build();
    }

    /**
     * @param username f.e. @username
     */
    @Nullable
    public Long getChatMembersCount(String username) {
        Request request = new Request.Builder().url(
            baseUri + "/getChatMembersCount?chat_id=" + username
        ).build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                JsonParser parser = new JsonParser();
                JsonObject o = parser.parse(checkNotNull(response.body()).string()).getAsJsonObject();
                if (o.get("ok").getAsBoolean()) {
                    return o.get("result").getAsLong();
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return null;
    }

    public boolean kickChatMember(long userId, long chatId, Instant untilDate) {
        Request request = new Request.Builder().url(
            baseUri + "/kickChatMember?" +
                RequestParamBuilder.builder()
                    .addParam("chat_id", chatId)
                    .addParam("user_id", userId)
                    .addParam("until_date", untilDate.getEpochSecond())
                    .build()
        ).build();
        try (Response response = httpClient.newCall(request).execute()) {
            ResponseBody body = response.body();
            if (response.isSuccessful() && body != null) {
                JsonParser parser = new JsonParser();
                JsonObject o = parser.parse(body.string()).getAsJsonObject();
                if (o.get("ok").getAsBoolean()) {
                    return o.get("result").getAsBoolean();
                }
            }
            throw new RuntimeException("Failed response");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void deleteMessage(long chatId, long messageId) {
        Request request = new Request.Builder().url(
            baseUri + "/deleteMessage?" +
                RequestParamBuilder.builder()
                    .addParam("chat_id", chatId)
                    .addParam("message_id", messageId)
                    .build()
        ).build();
        try (Response response = httpClient.newCall(request).execute()) {
            LOGGER.info(Objects.requireNonNull(response.body()).string());
            //message may be already deleted, so we don't worry
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    //TODO consider response
    public void answerInlineQuery(InlineQueryAnswer queryAnswer) {
        Request request = new Request.Builder()
            .url(baseUri + "/answerInlineQuery")
            .post(RequestBody.create(jsonHelper.serialize(queryAnswer), JSON_MEDIA_TYPE))
            .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("response is not successful");
            }
        } catch (Throwable e) {
            LOGGER.error("inlineQueryAnswer sending failed. ", e);
        }
    }

    @Nullable
    public SendMessageResponse sendMessage(SendMessageRequest requestBody) {
        Request request = new Request.Builder()
            .url(baseUri + "/sendMessage")
            .post(RequestBody.create(jsonHelper.serialize(requestBody), JSON_MEDIA_TYPE))
            .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                SendMessageResponse messageResponse = jsonHelper.deserialize(
                    checkNotNull(response.body()).string(),
                    SendMessageResponse.class
                );
                if (messageResponse.ok) {
                    return messageResponse;
                }
            }
        } catch (Throwable e) {
            LOGGER.error("Message sending failed. ", e);
        }
        return null;
    }

    @Nullable
    public Boolean edit(EditMessageTextRequest editMessageTextRequest) {
        //TODO Refactor
        Request request = new Request.Builder()
            .url(baseUri + "/editMessageText")
            .post(RequestBody.create(jsonHelper.serialize(editMessageTextRequest), JSON_MEDIA_TYPE))
            .build();
        try (Response response = httpClient.newCall(request).execute()) {
            ResponseBody body = response.body();
            if (response.isSuccessful() && body != null) {
                JsonParser parser = new JsonParser();
                JsonObject o = parser.parse(body.string()).getAsJsonObject();
                return o.get("ok").getAsBoolean();
            }
            return false;
        } catch (Throwable e) {
            LOGGER.error("Message sending failed. ", e);
        }
        return null;
    }

//    public void deleteMessage(long chatId, long messageId) {
//        Request request = new Request.Builder()
//            .url(String.format(TG_DELETE_MESSAGE_URL, apiKey, chatId, messageId))
//            .build();
//        //noinspection EmptyTryBlock
//        try (Response ignored = httpClient.newCall(request).execute()) {
//            //nothing is needed
//        } catch (Throwable e) {
//            LOGGER.error("Message deleting failed. ", e);
//        }
//    }

    public void editMessageReplyMarkup(EditMessageReplyMarkup requestBody) {
        Request request = new Request.Builder()
            .url(baseUri + "/editMessageReplyMarkup")
            .post(RequestBody.create(jsonHelper.serialize(requestBody), JSON_MEDIA_TYPE))
            .build();
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful() || response.body() == null) {
                throw new RuntimeException("response is not successful or body is empty");
            }
        } catch (Throwable e) {
            LOGGER.error("editMessageReplyMarkup failed. ", e);
        }
    }

    //TODO consider response
    public void sendPhoto(SendPhotoRequest requestBody) {
        Request request = new Request.Builder()
            .url(baseUri + "/sendPhoto")
            .post(RequestBody.create(jsonHelper.serialize(requestBody), JSON_MEDIA_TYPE))
            .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful() || response.body() == null) {
                throw new RuntimeException("response is not successful or body is empty");
            }
        } catch (Throwable e) {
            LOGGER.error("Message sending failed. ", e);
        }
    }

    public void answerCallbackQuery(CallbackQueryAnswer requestBody) {
        Request request = new Request.Builder()
            .url(baseUri + "/answerCallbackQuery")
            .post(RequestBody.create(jsonHelper.serialize(requestBody), JSON_MEDIA_TYPE))
            .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful() || response.body() == null) {
                throw new RuntimeException("response is not successful or body is empty");
            }
        } catch (Throwable e) {
            LOGGER.error("Message sending failed. ", e);
        }
    }
}
