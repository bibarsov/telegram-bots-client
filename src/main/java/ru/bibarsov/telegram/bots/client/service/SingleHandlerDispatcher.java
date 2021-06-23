package ru.bibarsov.telegram.bots.client.service;

import ru.bibarsov.telegram.bots.client.dto.Update;
import ru.bibarsov.telegram.bots.client.service.handler.Handler;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class SingleHandlerDispatcher implements Dispatcher {

    protected final Router router;
    protected final Handler<?> globalHandler;

    public SingleHandlerDispatcher(
        int workersThreadCount,
        Handler<?> globalHandler
    ) {
        this.globalHandler = globalHandler;
        this.router = new Router(workersThreadCount);
    }

    /**
     * Update dispatcher
     * Invokes handler by command by default
     * <p>
     * Meant to be overriden
     */
    @Override
    public void dispatch(Update update) {
        router.route(getQualifier(update), () -> globalHandler.handleUpdate(update));
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
