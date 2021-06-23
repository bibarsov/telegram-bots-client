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
public class EditMessageReplyMarkup {

    @JsonProperty("inline_message_id")
    @Nullable
    public final String inlineMessageId;

    @JsonProperty("reply_markup")
    @Nullable
    public final InlineKeyboardMarkup replyMarkup;

    @JsonCreator
    public EditMessageReplyMarkup(
        @JsonProperty("inline_message_id") @Nullable String inlineMessageId,
        @JsonProperty("reply_markup") @Nullable InlineKeyboardMarkup replyMarkup
    ) {
        this.inlineMessageId = inlineMessageId;
        this.replyMarkup = replyMarkup;
    }
}
