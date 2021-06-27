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
public class PhotoSize {

    @JsonProperty("file_id")
    public final String fileId;

    @JsonProperty("file_unique_id")
    public final String fileUniqueId;

    @JsonProperty("width")
    public final int width;

    @JsonProperty("height")
    public final int height;

    @JsonProperty("file_size")
    @Nullable
    public final Integer fileSize;

    @JsonCreator
    public PhotoSize(
        @JsonProperty("file_id") String fileId,
        @JsonProperty("file_unique_id") String fileUniqueId,
        @JsonProperty("width") int width,
        @JsonProperty("height") int height,
        @JsonProperty("file_size") @Nullable Integer fileSize
    ) {
        this.fileId = fileId;
        this.fileUniqueId = fileUniqueId;
        this.width = width;
        this.height = height;
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
