package bibarsov.telegram.bots.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Immutable
@ParametersAreNonnullByDefault
public class InlineKeyboardMarkup {

    @NotNull
    @JsonProperty("inline_keyboard")
    public final List<List<InlineKeyboardButton>> inlineKeyboard;

    @JsonCreator
    public InlineKeyboardMarkup(
            @JsonProperty("inline_keyboard") List<List<InlineKeyboardButton>> inlineKeyboard
    ) {
        this.inlineKeyboard = inlineKeyboard;
    }
}
