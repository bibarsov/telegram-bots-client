package bibarsov.telegram.bots.client.dto;

import bibarsov.telegram.bots.client.value.MessageEntityType;
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
public class MessageEntity {

    @NotNull
    @JsonProperty("type")
    public final MessageEntityType type;

    @NotNull
    @JsonProperty("offset")
    public final Integer offset;

    @NotNull
    @JsonProperty("length")
    public final Integer length;

    @JsonProperty("url")
    @Nullable
    public final String url;

    @JsonProperty("user")
    @Nullable
    public final String user;

    @JsonCreator
    public MessageEntity(
            @JsonProperty("type") MessageEntityType type,
            @JsonProperty("offset") Integer offset,
            @JsonProperty("length") Integer length,
            @JsonProperty("url") @Nullable String url,
            @JsonProperty("user") @Nullable String user
    ) {
        this.type = type;
        this.offset = offset;
        this.length = length;
        this.url = url;
        this.user = user;
    }
}
