package ru.bibarsov.telegram.bots.client.service;

import ru.bibarsov.telegram.bots.client.dto.Update;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface Dispatcher {

    void dispatch(Update update);
}
