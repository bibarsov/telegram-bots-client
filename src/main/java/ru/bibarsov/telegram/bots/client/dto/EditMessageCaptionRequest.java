package ru.bibarsov.telegram.bots.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.validation.constraints.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Immutable
@ParametersAreNonnullByDefault
public class EditMessageCaptionRequest {

    @Pattern(regexp = "\\d+|@[A-z0-9_]{5,}")
    @JsonProperty("chat_id")
    @Nullable
    public final String chatId;

    @JsonProperty("message_id")
    @Nullable
    public final Long messageId;

    @JsonProperty("inline_message_id")
    @Nullable
    public final String inlineMessageId;

    @JsonProperty("caption")
    @Nullable
    public final String caption;

    @JsonProperty("parse_mode")
    @Nullable
    public final String parseMode;

    @JsonProperty("reply_markup")
    @Nullable
    public final InlineKeyboardMarkup replyMarkup;

    @JsonCreator
    public EditMessageCaptionRequest(
        @JsonProperty("chat_id") @Nullable String chatId,
        @JsonProperty("message_id") @Nullable Long messageId,
        @JsonProperty("inline_message_id") @Nullable String inlineMessageId,
        @JsonProperty("caption") @Nullable String caption,
        @JsonProperty("parse_mode") @Nullable String parseMode,
        @JsonProperty("reply_markup") @Nullable InlineKeyboardMarkup replyMarkup
    ) {
        this.chatId = chatId;
        this.messageId = messageId;
        this.inlineMessageId = inlineMessageId;
        this.caption = caption;
        this.parseMode = parseMode;
        this.replyMarkup = replyMarkup;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
