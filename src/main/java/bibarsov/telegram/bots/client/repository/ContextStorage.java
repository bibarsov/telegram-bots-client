package bibarsov.telegram.bots.client.repository;

import bibarsov.telegram.bots.client.dto.Update;

public interface ContextStorage<T extends Enum<T>> {

    void putContext(Update update, T o);
    T find(Update update);
}
