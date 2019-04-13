package bibarsov.telegram.bots.client.service.handler;

import bibarsov.telegram.bots.client.dto.CallbackQuery;
import bibarsov.telegram.bots.client.dto.Message;
import bibarsov.telegram.bots.client.dto.PhotoSize;
import bibarsov.telegram.bots.client.dto.Update;

import javax.annotation.Nullable;
import java.util.List;

public class Handler<T extends Enum<T>> {

    public final void handleUpdate(Update update) {
        handle(update);
        if (update.message != null) handle(update.message);
        if (update.callbackQuery != null) handle(update.callbackQuery);
        if (update.photo != null) handle(update.photo);
    }

    public void handle(Update update) {
        //ignore by default
    }

    public void handle(Message message) {
        //ignore by default
    }

    public void handle(List<PhotoSize> photoSizes) {
        //ignore by default
    }

    public void handle(CallbackQuery callbackQuery) {
        //ignore by default
    }

    /**
     * Supposed to be enum instance on which the context is fixed.
     * If not overriden - works by default when dispatcher can't find
     * the current context.
     */
    @Nullable
    public T getCommand() {
        return null;
    }
}
