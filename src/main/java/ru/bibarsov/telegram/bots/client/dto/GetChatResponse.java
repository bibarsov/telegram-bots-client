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
public class GetChatResponse {

    @JsonProperty("ok")
    public final boolean ok;

    @JsonProperty("result")
    @Nullable
    public final Chat result;

    @JsonCreator
    public GetChatResponse(
        @JsonProperty("ok") boolean ok,
        @JsonProperty("result") @Nullable Chat result
    ) {
        this.ok = ok;
        this.result = result;
    }
}
