package ru.bibarsov.telegram.bots.client.exception;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class TelegramException extends RuntimeException {

    public final int errorCode;
    public final String description;

    public TelegramException(int errorCode, String description) {
        super();
        this.errorCode = errorCode;
        this.description = description;
    }
}
