package bibarsov.telegram.bots.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Immutable
@ParametersAreNonnullByDefault
public class SendMessageResponse {

    @NotNull
    @JsonProperty("ok")
    public final Boolean ok;

    @JsonProperty("result")
    @Nullable
    public final Message result;

    @JsonCreator
    public SendMessageResponse(
            @JsonProperty("ok") Boolean ok,
            @JsonProperty("result") @Nullable Message result
    ) {
        this.ok = ok;
        this.result = result;
    }
}
