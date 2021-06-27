package ru.bibarsov.telegram.bots.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Immutable
@ParametersAreNonnullByDefault
public class InlineQueryAnswer {

    @JsonProperty("inline_query_id")
    public final String inlineQueryResId;

    @JsonProperty("results")
    public final List<Object> results;

    @JsonCreator
    public InlineQueryAnswer(
        @JsonProperty("inline_query_id") String inlineQueryResId,
        @JsonProperty("results") List<Object> results
    ) {
        this.inlineQueryResId = inlineQueryResId;
        this.results = results;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
