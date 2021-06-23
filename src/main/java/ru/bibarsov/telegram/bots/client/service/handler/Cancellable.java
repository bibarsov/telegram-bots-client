package ru.bibarsov.telegram.bots.client.service.handler;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface Cancellable {
    void onCancel(long userId);
}
