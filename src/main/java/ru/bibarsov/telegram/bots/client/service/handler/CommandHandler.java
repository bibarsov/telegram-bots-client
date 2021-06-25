package ru.bibarsov.telegram.bots.client.service.handler;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public abstract class CommandHandler<T extends Enum<T>> extends Handler {

    /**
     * Supposed to be enum instance on which the context is fixed.
     */
    public abstract T getCommand();
}
