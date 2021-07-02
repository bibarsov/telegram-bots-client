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
public class InputTextMessageContent implements InputMessageContent {

    @JsonProperty("message_text")
    public final String messageText;

    @JsonProperty("parse_mode")
    @Nullable
    public final String parseMode;

    @JsonCreator
    public InputTextMessageContent(
        @JsonProperty("message_text") String messageText,
        @JsonProperty("parse_mode") @Nullable String parseMode
    ) {
        this.messageText = messageText;
        this.parseMode = parseMode;
    }

    public InputTextMessageContent(@JsonProperty("message_text") String messageText) {
        this.messageText = messageText;
        this.parseMode = null;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
