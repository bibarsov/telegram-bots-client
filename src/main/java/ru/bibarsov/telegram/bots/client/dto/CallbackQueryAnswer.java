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
public class CallbackQueryAnswer {

    @JsonProperty("callback_query_id")
    public final String callbackQueryId;

    @JsonProperty("text")
    @Nullable
    public final String text;

    @JsonProperty("show_alert")
    @Nullable
    public final Boolean showAlert;

    @JsonCreator
    public CallbackQueryAnswer(
        @JsonProperty("callback_query_id") String callbackQueryId,
        @JsonProperty("text") @Nullable String text,
        @JsonProperty("show_alert") @Nullable Boolean showAlert
    ) {
        this.callbackQueryId = callbackQueryId;
        this.text = text;
        this.showAlert = showAlert;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
