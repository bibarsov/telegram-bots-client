package ru.bibarsov.telegram.bots.client.value;

import javax.annotation.concurrent.Immutable;

@Immutable
public enum ChatType {

    PRIVATE,
    GROUP,
    SUPERGROUP,
    CHANNEL
}
