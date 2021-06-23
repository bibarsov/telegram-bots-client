package ru.bibarsov.telegram.bots.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Immutable
@ParametersAreNonnullByDefault
public class InlineQueryResultCachedPhoto {

    @JsonProperty("type")
    public final String type;

    @JsonProperty("id")
    public final String id;

    @JsonProperty("photo_file_id")
    public final String photoFileId;

    @JsonProperty("caption")
    @Nullable
    public final String caption;

    @JsonProperty("reply_markup")
    @Nullable
    public final InlineKeyboardMarkup replyMarkup;

    @JsonCreator
    public InlineQueryResultCachedPhoto(
        @JsonProperty("type") String type,
        @JsonProperty("id") String id,
        @JsonProperty("photo_file_id") String photoFileId,
        @JsonProperty("caption") @Nullable String caption,
        @JsonProperty("reply_markup") @Nullable InlineKeyboardMarkup replyMarkup
    ) {
        this.type = type;
        this.id = id;
        this.photoFileId = photoFileId;
        this.caption = caption;
        this.replyMarkup = replyMarkup;
    }
}
