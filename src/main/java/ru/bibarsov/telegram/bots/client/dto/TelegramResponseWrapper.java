package ru.bibarsov.telegram.bots.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

//TODO Apply everythere
@JsonInclude(JsonInclude.Include.NON_NULL)
@Immutable
@ParametersAreNonnullByDefault
public class TelegramResponseWrapper<T> {

    @JsonProperty("ok")
    public final boolean ok;

    @JsonProperty("result")
    @Nullable
    public final T result;

    @JsonProperty("error_code")
    @Nullable
    public final Integer errorCode;

    @JsonProperty("description")
    @Nullable
    public final String description;

    @JsonCreator
    public TelegramResponseWrapper(
        @JsonProperty("ok") boolean ok,
        @JsonProperty("result") @Nullable T result,
        @JsonProperty("error_code") @Nullable Integer errorCode,
        @JsonProperty("description") @Nullable String description
    ) {
        this.ok = ok;
        this.result = result;
        this.errorCode = errorCode;
        this.description = description;
    }
}
