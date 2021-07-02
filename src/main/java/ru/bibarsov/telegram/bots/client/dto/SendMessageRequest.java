package ru.bibarsov.telegram.bots.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Immutable
@ParametersAreNonnullByDefault
public class SendMessageRequest {

    @JsonProperty("chat_id")
    public final Object chatId; //Integer or String

    @JsonProperty("text")
    public final String text;

    @JsonProperty("parse_mode")
    @Nullable
    public final String parseMode;

    @JsonProperty("disable_web_page_preview")
    @Nullable
    public final Boolean disableWebPagePreview;

    @JsonProperty("reply_markup")
    @Nullable
    public final Object replyMarkup;

    @JsonCreator
    public SendMessageRequest(
        @JsonProperty("chat_id") Object chatId,
        @JsonProperty("text") String text,
        @JsonProperty("parse_mode") @Nullable String parseMode,
        @JsonProperty("disable_web_page_preview") @Nullable Boolean disableWebPagePreview,
        @JsonProperty("reply_markup") @Nullable Object replyMarkup
    ) {
        this.chatId = chatId;
        this.text = text;
        this.parseMode = parseMode;
        this.disableWebPagePreview = disableWebPagePreview;
        this.replyMarkup = replyMarkup;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
