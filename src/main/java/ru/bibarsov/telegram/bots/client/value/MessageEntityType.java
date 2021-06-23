package ru.bibarsov.telegram.bots.client.value;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

@Immutable
@ParametersAreNonnullByDefault
public enum MessageEntityType {

    MENTION,
    HASHTAG,
    CASHTAG,
    BOT_COMMAND,
    PHONE_NUMBER,
    URL,
    EMAIL,
    BOLD,
    ITALIC,
    CODE,
    PRE,
    TEXT_LINK,
    TEXT_MENTION,
    UNKNOWN
}
