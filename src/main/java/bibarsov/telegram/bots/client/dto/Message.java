package bibarsov.telegram.bots.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Immutable
@ParametersAreNonnullByDefault
public class Message {

    @NotNull
    @JsonProperty("message_id")
    public final Long messageId;

    @JsonProperty("from")
    @Nullable
    public final User from;

    @NotNull
    @JsonProperty("chat")
    public final Chat chat;

    @NotNull
    @JsonProperty("date")
    public final Date date;

    @JsonProperty("text")
    @Nullable
    public final String text;

    @JsonProperty("entities")
    @Nullable
    public final List<MessageEntity> entities;

    @JsonCreator
    public Message(
            @JsonProperty("message_id") Long messageId,
            @JsonProperty("from") @Nullable User from,
            @JsonProperty("chat") Chat chat,
            @JsonProperty("date") Date date,
            @JsonProperty("text") @Nullable String text,
            @JsonProperty("entities") @Nullable List<MessageEntity> entities
    ) {
        this.messageId = messageId;
        this.from = from;
        this.chat = chat;
        this.date = date;
        this.text = text;
        this.entities = entities;
    }
}
