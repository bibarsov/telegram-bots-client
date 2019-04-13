package bibarsov.telegram.bots.client.service;

import bibarsov.telegram.bots.client.dto.Update;
import bibarsov.telegram.bots.client.service.handler.Handler;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public abstract class Dispatcher<T extends Enum<T>> {

    private final ExecutorServiceRouter router;
    private final Handler[] handlers;

    public Dispatcher(
            int workersThreadCount,
            Handler[] handlers
    ) {
        this.handlers = handlers;
        this.router = new ExecutorServiceRouter(workersThreadCount);
    }

    /**
     * Update dispatcher
     * Invokes handler by command by default
     * <p>
     * Meant to be overriden
     */
    protected void dispatch(Update update) {
        T context = extractContext(update);

        processContext(context);

        for (Handler handler : handlers) {
            if (handler.getCommand() == context) {
                router.route(hashFromUpdate(update), () -> handler.handleUpdate(update));
            }
        }
    }

    @Nullable
    protected abstract T extractContext(Update update);

    protected abstract void processContext(@Nullable T context);

    /**
     * Split up the tasks by multiple threads based on arbitrary hash
     *
     * @return hashcode
     */
    protected abstract int hashFromUpdate(Update update);
}
