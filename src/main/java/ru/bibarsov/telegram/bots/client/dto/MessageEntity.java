package ru.bibarsov.telegram.bots.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import ru.bibarsov.telegram.bots.client.value.MessageEntityType;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Immutable
@ParametersAreNonnullByDefault
public class MessageEntity {

    @JsonProperty("type")
    public final MessageEntityType type;

    @JsonProperty("offset")
    public final int offset;

    @JsonProperty("length")
    public final int length;

    @JsonProperty("url")
    @Nullable
    public final String url;

    @JsonProperty("user")
    @Nullable
    public final String user;

    @JsonCreator
    public MessageEntity(
        @JsonProperty("type") MessageEntityType type,
        @JsonProperty("offset") int offset,
        @JsonProperty("length") int length,
        @JsonProperty("url") @Nullable String url,
        @JsonProperty("user") @Nullable String user
    ) {
        this.type = type;
        this.offset = offset;
        this.length = length;
        this.url = url;
        this.user = user;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
