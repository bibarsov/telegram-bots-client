package bibarsov.telegram.bots.client.service;

import bibarsov.telegram.bots.client.dto.Update;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface Dispatcher<T extends Enum<T>> {

    void dispatch(Update update);
}
