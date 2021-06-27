package ru.bibarsov.telegram.bots.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Immutable
@ParametersAreNonnullByDefault
public class InlineQuery {

    @JsonProperty("id")
    public final String id;

    @JsonProperty("from")
    public final User from;

    @JsonProperty("query")
    public final String query;

    @JsonProperty("offset")
    public final String offset;

    @JsonCreator
    public InlineQuery(
        @JsonProperty("id") String id,
        @JsonProperty("from") User from,
        @JsonProperty("query") String query,
        @JsonProperty("offset") String offset
    ) {
        this.id = id;
        this.from = from;
        this.query = query;
        this.offset = offset;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
