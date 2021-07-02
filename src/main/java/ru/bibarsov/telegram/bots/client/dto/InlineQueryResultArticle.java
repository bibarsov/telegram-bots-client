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
public class InlineQueryResultArticle {

    @JsonProperty("type")
    public final String type;

    @JsonProperty("id")
    public final String id;

    @JsonProperty("title")
    public final String title;

    @JsonProperty("description")
    @Nullable
    public final String description;

    @JsonProperty("url")
    @Nullable
    public final String url;

    @JsonProperty("hide_url")
    @Nullable
    private final Boolean hideUrl;

    @JsonProperty("input_message_content")
    public final InputMessageContent content;

    @JsonProperty("reply_markup")
    @Nullable
    public final InlineKeyboardMarkup replyMarkup;

    @JsonCreator
    public InlineQueryResultArticle(
        @JsonProperty("type") String type,
        @JsonProperty("id") String id,
        @JsonProperty("title") String title,
        @JsonProperty("description") @Nullable String description,
        @JsonProperty("url") @Nullable String url,
        @JsonProperty("hide_url") @Nullable Boolean hideUrl,
        @JsonProperty("input_message_content") InputMessageContent content,
        @JsonProperty("reply_markup") @Nullable InlineKeyboardMarkup replyMarkup
    ) {
        this.type = type;
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.hideUrl = hideUrl;
        this.content = content;
        this.replyMarkup = replyMarkup;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
