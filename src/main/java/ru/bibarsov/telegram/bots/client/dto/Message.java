package ru.bibarsov.telegram.bots.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Immutable
@ParametersAreNonnullByDefault
public class Message {

    @JsonProperty("message_id")
    public final long messageId;

    @JsonProperty("from")
    @Nullable
    public final User from;

    @JsonProperty("chat")
    public final Chat chat;

    @JsonProperty("forward_from")
    @Nullable
    public final User forwardFrom;

    @JsonProperty("date")
    public final long date;

    @JsonProperty("edit_date")
    @Nullable
    public final Long editDate;

    @JsonProperty("text")
    @Nullable
    public final String text;

    @JsonProperty("entities")
    @Nullable
    public final List<MessageEntity> entities;

    @JsonProperty(value = "new_chat_members")
    @Nullable
    public final List<User> newChatMembers;

    @JsonProperty(value = "media_group_id")
    @Nullable
    public final Long mediaGroupId;

    @JsonProperty(value = "photo")
    @Nullable
    public final List<PhotoSize> photo;

    @JsonProperty("caption")
    @Nullable
    public final String caption;

    @JsonCreator
    public Message(
        @JsonProperty("message_id") long messageId,
        @JsonProperty("from") @Nullable User from,
        @JsonProperty("chat") Chat chat,
        @JsonProperty("forward_from") @Nullable User forwardFrom,
        @JsonProperty("date") long date,
        @JsonProperty("edit_date") @Nullable Long editDate,
        @JsonProperty("text") @Nullable String text,
        @JsonProperty("entities") @Nullable List<MessageEntity> entities,
        @JsonProperty("new_chat_members") @Nullable List<User> newChatMembers,
        @JsonProperty("media_group_id") @Nullable Long mediaGroupId,
        @JsonProperty("photo") @Nullable List<PhotoSize> photo,
        @JsonProperty("caption") @Nullable String caption
    ) {
        this.messageId = messageId;
        this.from = from;
        this.chat = chat;
        this.forwardFrom = forwardFrom;
        this.date = date;
        this.editDate = editDate;
        this.text = text;
        this.entities = entities;
        this.newChatMembers = newChatMembers;
        this.mediaGroupId = mediaGroupId;
        this.photo = photo;
        this.caption = caption;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
