package bibarsov.telegram.bots.client.dto;

import bibarsov.telegram.bots.client.value.ChatType;
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
public class Chat {

    @NotNull
    @JsonProperty("id")
    public final Long id;

    @JsonProperty("title")
    @Nullable
    public final String title;

    @JsonProperty("username")
    @Nullable
    public final String username;

    @JsonProperty("description")
    @Nullable
    public final String description;

    @NotNull
    @JsonProperty("type")
    public final ChatType type;

    @JsonCreator
    public Chat(
            @JsonProperty("id") Long id,
            @JsonProperty("title") @Nullable String title,
            @JsonProperty("username") @Nullable String username,
            @JsonProperty("description") @Nullable String description,
            @JsonProperty("type") ChatType type
    ) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.description = description;
        this.type = type;
    }
}
