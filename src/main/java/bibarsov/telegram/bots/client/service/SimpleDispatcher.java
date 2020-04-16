package bibarsov.telegram.bots.client.service;

import bibarsov.telegram.bots.client.dto.Update;
import bibarsov.telegram.bots.client.service.handler.Handler;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ParametersAreNonnullByDefault
public class SimpleDispatcher<T extends Enum<T>> implements Dispatcher<T> {

    @ParametersAreNonnullByDefault
    public static class Router {
        private final List<ExecutorService> services;
        private final int size;

        public Router(int size) {
            this.services = new ArrayList<>(size);
            this.size = size;
            for (int i = 0; i < size; i++) {
                this.services.add(Executors.newSingleThreadExecutor());
            }
        }

        public void route(long id, Runnable runnable) {
            services.get((int) (id % size)).execute(runnable);
        }
    }

    private final Router router;
    private final Handler[] handlers;
    private final EnumSet<T> enumSet;

    public SimpleDispatcher(
            int workersThreadCount,
            Handler[] handlers,
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
        for (Handler handler : handlers) {
            if (handler.getCommand() == context) {
                router.route(getQualifier(update), () -> handler.handleUpdate(update));
            }
        }
    }

    /**
     * Logic of extracting context / command. Override to change
     */
    @Nullable
    private T extractContext(Update update) {
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
    private long getQualifier(Update update) {
        return update.updateId;
    }
}
