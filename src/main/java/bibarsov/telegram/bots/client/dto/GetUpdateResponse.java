package bibarsov.telegram.bots.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Immutable
@ParametersAreNonnullByDefault
public class GetUpdateResponse {

    @NotNull
    @JsonProperty("ok")
    public final Boolean ok;

    @JsonProperty("result")
    @Nullable
    public final List<Update> result;

    @JsonCreator
    public GetUpdateResponse(
            @JsonProperty("ok") Boolean ok,
            @JsonProperty("result") @Nullable List<Update> result
    ) {
        this.ok = ok;
        this.result = result;
    }
}
