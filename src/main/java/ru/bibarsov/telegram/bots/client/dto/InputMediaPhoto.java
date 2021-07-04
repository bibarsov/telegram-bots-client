package ru.bibarsov.telegram.bots.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Immutable
@ParametersAreNonnullByDefault
public class InputMediaPhoto implements InputMedia {

    @JsonProperty("type")
    public final String type;

    @JsonProperty("media")
    public final String media;

    @JsonProperty("caption")
    @Nullable
    public final String caption;

    @JsonProperty("parse_mode")
    @Nullable
    public final String parseMode;

    @JsonCreator
    public InputMediaPhoto(
        @JsonProperty("type") String type,
        @JsonProperty("media") String media,
        @JsonProperty("caption") @Nullable String caption,
        @JsonProperty("parse_mode") @Nullable String parseMode
    ) {
        this.type = type;
        this.media = media;
        this.caption = caption;
        this.parseMode = parseMode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
