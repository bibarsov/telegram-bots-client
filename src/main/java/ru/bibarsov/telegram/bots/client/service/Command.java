package ru.bibarsov.telegram.bots.client.service;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface Command<T extends Enum<T>> {

    String getValue();

    T getSelf();
}
