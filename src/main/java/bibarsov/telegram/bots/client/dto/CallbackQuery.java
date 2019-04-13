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
public class CallbackQuery {

    @NotNull
    @JsonProperty("id")
    public final String id;

    @NotNull
    @JsonProperty("from")
    public final User from;

    @JsonProperty("message")
    @Nullable
    public final Message message;

    @JsonProperty("inline_message_id")
    @Nullable
    public final String inlineMessageId;

    @NotNull
    @JsonProperty("chat_instance")
    public final String chatInstance;

    @JsonProperty("data")
    @Nullable
    public final String data;

    @JsonProperty("game_short_name")
    @Nullable
    public final String gameShortName;

    @JsonCreator
    public CallbackQuery(
            @JsonProperty("id") String id,
            @JsonProperty("from") User from,
            @JsonProperty("message") @Nullable Message message,
            @JsonProperty("inline_message_id") @Nullable String inlineMessageId,
            @JsonProperty("chat_instance") String chatInstance,
            @JsonProperty("data") @Nullable String data,
            @JsonProperty("game_short_name") @Nullable String gameShortName
    ) {
        this.id = id;
        this.from = from;
        this.message = message;
        this.inlineMessageId = inlineMessageId;
        this.chatInstance = chatInstance;
        this.data = data;
        this.gameShortName = gameShortName;
    }
}
