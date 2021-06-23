package ru.bibarsov.telegram.bots.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.validation.constraints.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Immutable
@ParametersAreNonnullByDefault
public class EditMessageTextRequest {

    @Pattern(regexp = "\\d+|@[A-z0-9_]{5,}")
    @JsonProperty("chat_id")
    @Nullable
    public final String chatId;

    @JsonProperty("message_id")
    @Nullable
    public final Long messageId;

    @JsonProperty("inline_message_id")
    @Nullable
    public final Long inlineMessageId;

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
    public final InlineKeyboardMarkup replyMarkup;

    @JsonCreator
    public EditMessageTextRequest(
            @JsonProperty("chat_id") @Nullable String chatId,
            @JsonProperty("message_id") @Nullable Long messageId,
            @JsonProperty("inline_message_id") @Nullable Long inlineMessageId,
            @JsonProperty("text") String text,
            @JsonProperty("parse_mode") @Nullable String parseMode,
            @JsonProperty("disable_web_page_preview") @Nullable Boolean disableWebPagePreview,
            @JsonProperty("reply_markup") @Nullable InlineKeyboardMarkup replyMarkup
    ) {
        this.chatId = chatId;
        this.messageId = messageId;
        this.inlineMessageId = inlineMessageId;
        this.text = text;
        this.parseMode = parseMode;
        this.disableWebPagePreview = disableWebPagePreview;
        this.replyMarkup = replyMarkup;
    }
}
