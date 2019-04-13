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
public class PhotoSize {

    @NotNull
    @JsonProperty("file_id")
    public final String fileId;

    @NotNull
    @JsonProperty("width")
    public final Integer width;

    @NotNull
    @JsonProperty("height")
    public final Integer height;

    @JsonProperty("file_size")
    @Nullable
    public final Integer fileSize;

    @JsonCreator
    public PhotoSize(
            @JsonProperty("file_id") String fileId,
            @JsonProperty("width") Integer width,
            @JsonProperty("height") Integer height,
            @JsonProperty("file_size") @Nullable Integer fileSize
    ) {
        this.fileId = fileId;
        this.width = width;
        this.height = height;
        this.fileSize = fileSize;
    }
}
