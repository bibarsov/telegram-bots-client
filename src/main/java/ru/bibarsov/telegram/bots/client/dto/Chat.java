package ru.bibarsov.telegram.bots.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.bibarsov.telegram.bots.client.value.ChatType;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Immutable
@ParametersAreNonnullByDefault
public class Chat {

    @JsonProperty("id")
    public final long id;

    @JsonProperty("title")
    @Nullable
    public final String title;

    @JsonProperty("username")
    @Nullable
    public final String username;

    @JsonProperty("description")
    @Nullable
    public final String description;

    @JsonProperty("type")
    public final ChatType type;

    @JsonCreator
    public Chat(
            @JsonProperty("id") long id,
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
