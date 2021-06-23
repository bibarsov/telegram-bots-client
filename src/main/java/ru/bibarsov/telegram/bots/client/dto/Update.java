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
public class Update {

    @JsonProperty("update_id")
    public final long updateId;

    @JsonProperty("message")
    @Nullable
    public final Message message;

    @JsonProperty("edited_message")
    @Nullable
    public final Message editedMessage;

    @JsonProperty("callback_query")
    @Nullable
    public final CallbackQuery callbackQuery;

    @JsonProperty("inline_query")
    @Nullable
    public final InlineQuery inlineQuery;

    @JsonCreator
    public Update(
        @JsonProperty("update_id") long updateId,
        @JsonProperty("message") @Nullable Message message,
        @JsonProperty("edited_message") @Nullable Message editedMessage,
        @JsonProperty("callback_query") @Nullable CallbackQuery callbackQuery,
        @JsonProperty("inline_query") @Nullable InlineQuery inlineQuery
    ) {
        this.updateId = updateId;
        this.message = message;
        this.editedMessage = editedMessage;
        this.callbackQuery = callbackQuery;
        this.inlineQuery = inlineQuery;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
