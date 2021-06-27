package ru.bibarsov.telegram.bots.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Immutable
@ParametersAreNonnullByDefault
public class ChatMember {

    @JsonProperty("user")
    public final User user;

    @JsonProperty("status")
    public final String status;

    @JsonProperty("until_date")
    @Nullable
    public final Date untilDate;

    @JsonProperty("can_be_edited")
    @Nullable
    public final Boolean canBeEdited;

    @JsonProperty("can_change_info")
    @Nullable
    public final Boolean canChangeInfo;

    @JsonProperty("can_post_messages")
    @Nullable
    public final Boolean canPostMessages;

    @JsonProperty("can_edit_messages")
    @Nullable
    public final Boolean canEditMessages;

    @JsonProperty("can_delete_messages")
    @Nullable
    public final Boolean canDeleteMessages;

    @JsonProperty("can_invite_users")
    @Nullable
    public final Boolean canInviteUsers;

    @JsonProperty("can_restrict_members")
    @Nullable
    public final Boolean canRestrictMembers;

    @JsonProperty("can_pin_messages")
    @Nullable
    public final Boolean canPinMessages;

    @JsonProperty("can_promote_members")
    @Nullable
    public final Boolean canPromoteMembers;

    @JsonProperty("can_send_messages")
    @Nullable
    public final Boolean canSendMessages;

    @JsonProperty("can_send_media_messages")
    @Nullable
    public final Boolean canSendMediaMessages;

    @JsonProperty("can_send_other_messages")
    @Nullable
    public final Boolean canSendOtherMessages;

    @JsonProperty("can_add_web_page_previews")
    @Nullable
    public final Boolean canAddWebPagePreviews;

    @JsonCreator
    public ChatMember(
        @JsonProperty("user") User user,
        @JsonProperty("status") String status,
        @JsonProperty("until_date") @Nullable Date untilDate,
        @JsonProperty("can_be_edited") @Nullable Boolean canBeEdited,
        @JsonProperty("can_change_info") @Nullable Boolean canChangeInfo,
        @JsonProperty("can_post_messages") @Nullable Boolean canPostMessages,
        @JsonProperty("can_edit_messages") @Nullable Boolean canEditMessages,
        @JsonProperty("can_delete_messages") @Nullable Boolean canDeleteMessages,
        @JsonProperty("can_invite_users") @Nullable Boolean canInviteUsers,
        @JsonProperty("can_restrict_members") @Nullable Boolean canRestrictMembers,
        @JsonProperty("can_pin_messages") @Nullable Boolean canPinMessages,
        @JsonProperty("can_promote_members") @Nullable Boolean canPromoteMembers,
        @JsonProperty("can_send_messages") @Nullable Boolean canSendMessages,
        @JsonProperty("can_send_media_messages") @Nullable Boolean canSendMediaMessages,
        @JsonProperty("can_send_other_messages") @Nullable Boolean canSendOtherMessages,
        @JsonProperty("can_add_web_page_previews") @Nullable Boolean canAddWebPagePreviews
    ) {
        this.user = user;
        this.status = status;
        this.untilDate = untilDate;
        this.canBeEdited = canBeEdited;
        this.canChangeInfo = canChangeInfo;
        this.canPostMessages = canPostMessages;
        this.canEditMessages = canEditMessages;
        this.canDeleteMessages = canDeleteMessages;
        this.canInviteUsers = canInviteUsers;
        this.canRestrictMembers = canRestrictMembers;
        this.canPinMessages = canPinMessages;
        this.canPromoteMembers = canPromoteMembers;
        this.canSendMessages = canSendMessages;
        this.canSendMediaMessages = canSendMediaMessages;
        this.canSendOtherMessages = canSendOtherMessages;
        this.canAddWebPagePreviews = canAddWebPagePreviews;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
