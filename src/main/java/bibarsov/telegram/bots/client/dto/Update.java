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
public class Update {

    @NotNull
    @JsonProperty("update_id")
    public final Long updateId;

    @JsonProperty(value = "message")
    @Nullable
    public final Message message;

    @JsonProperty(value = "new_chat_members")
    @Nullable
    public final List<User> newChatMembers;

    @JsonProperty(value = "callback_query")
    @Nullable
    public final CallbackQuery callbackQuery;

    @JsonProperty(value = "photo")
    @Nullable
    public final List<PhotoSize> photo;

    @JsonCreator
    public Update(
            @JsonProperty("update_id") Long updateId,
            @JsonProperty("message") @Nullable Message message,
            @JsonProperty("new_chat_members") @Nullable List<User> newChatMembers,
            @JsonProperty("callback_query") @Nullable CallbackQuery callbackQuery,
            @JsonProperty("photo") @Nullable List<PhotoSize> photo
    ) {
        this.updateId = updateId;
        this.message = message;
        this.newChatMembers = newChatMembers;
        this.callbackQuery = callbackQuery;
        this.photo = photo;
    }
}
