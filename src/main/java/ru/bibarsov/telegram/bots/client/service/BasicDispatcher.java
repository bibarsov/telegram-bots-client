package ru.bibarsov.telegram.bots.client.service;

import ru.bibarsov.telegram.bots.client.dto.Update;
import ru.bibarsov.telegram.bots.client.service.handler.Handler;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.EnumSet;
import java.util.List;

@ParametersAreNonnullByDefault
public class BasicDispatcher<T extends Enum<T>> implements Dispatcher {

    protected final Router router;
    protected final List<Handler<T>> handlers;
    protected final EnumSet<T> enumSet;

    public BasicDispatcher(
        int workersThreadCount,
        List<Handler<T>> handlers,
        Class<T> enumClass
    ) {
        this.handlers = handlers;
        this.router = new Router(workersThreadCount);
        this.enumSet = EnumSet.allOf(enumClass);
    }

    /**
     * Update dispatcher
     * Invokes handler by command by default
     * <p>
     * Meant to be overriden
     */
    @Override
    public void dispatch(Update update) {
        T context = extractContext(update);
        for (Handler<T> handler : handlers) {
            if (handler.getCommand() == context) {
                router.route(getQualifier(update), () -> handler.handleUpdate(update));
            }
        }
    }

    /**
     * Logic of extracting context / command. Override to change
     */
    @Nullable
    protected T extractContext(Update update) {
        //extracting by message containing command if found by default
        if (update.message != null) {
            for (T t : enumSet) {
                if (String.format("/%s", t.name()).equalsIgnoreCase(update.message.text)) {
                    return t;
                }
            }
        }
        return null;
    }

    /**
     * Split up the tasks by multiple threads based on arbitrary qualifier
     * Override to change logic given by default (updateId),
     * maybe it would be more convenient to use UserId as qualifier,
     * so only one message is processed at once by a particular user
     */
    protected long getQualifier(Update update) {
        return update.updateId;
    }
}
