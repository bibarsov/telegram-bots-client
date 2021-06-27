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
public class InlineKeyboardButton {

    @JsonProperty("text")
    public final String text;

    @JsonProperty("callback_data")
    @Nullable
    public final String callbackData;

    @JsonProperty("switch_inline_query")
    @Nullable
    public final String switchInlineQuery;

    @JsonProperty("url")
    @Nullable
    public final String url;

    @JsonCreator
    public InlineKeyboardButton(
        @JsonProperty("text") String text,
        @JsonProperty("callback_data") @Nullable String callbackData,
        @JsonProperty("switch_inline_query") @Nullable String switchInlineQuery,
        @JsonProperty("url") @Nullable String url
    ) {
        this.text = text;
        this.callbackData = callbackData;
        this.switchInlineQuery = switchInlineQuery;
        this.url = url;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
