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
public class InlineKeyboardButton {

    @NotNull
    @JsonProperty("text")
    public final String text;

    @JsonProperty("callback_data")
    @Nullable
    public final String callbackData;

    @JsonCreator
    public InlineKeyboardButton(
            @JsonProperty("text") String text,
            @JsonProperty("callback_data") @Nullable String callbackData
    ) {
        this.text = text;
        this.callbackData = callbackData;
    }
}
