package ru.bibarsov.telegram.bots.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Immutable
@ParametersAreNonnullByDefault
public class SendPhotoRequest {

    @JsonProperty("chat_id")
    public final long chatId;

    @JsonProperty("photo")
    public final String photo;

    @JsonProperty("caption")
    @Nullable
    public final String caption;

    @JsonProperty("parse_mode")
    @Nullable
    public final String parseMode;

    @JsonProperty("reply_markup")
    @Nullable
    public final Object replyMarkup;

    @JsonCreator
    public SendPhotoRequest(
            @JsonProperty("chat_id") long chatId,
            @JsonProperty("photo") String photo,
            @JsonProperty("caption") @Nullable String caption,
            @JsonProperty("parse_mode") @Nullable String parseMode,
            @JsonProperty("reply_markup") @Nullable Object replyMarkup
    ) {
        this.chatId = chatId;
        this.photo = photo;
        this.caption = caption;
        this.parseMode = parseMode;
        this.replyMarkup = replyMarkup;
    }
}
