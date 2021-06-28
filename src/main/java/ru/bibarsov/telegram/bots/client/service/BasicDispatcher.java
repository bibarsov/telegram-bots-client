package ru.bibarsov.telegram.bots.client.service;

import ru.bibarsov.telegram.bots.client.dto.Update;
import ru.bibarsov.telegram.bots.client.service.handler.CommandHandler;
import ru.bibarsov.telegram.bots.client.service.handler.Handler;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.EnumSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;

@ParametersAreNonnullByDefault
public class BasicDispatcher<T extends Enum<T> & Command<T>> implements Dispatcher {

    private static final Pattern COMMAND_PATTERN = Pattern.compile("^/([A-z0-9_]+).*");

    protected final Router router;
    protected final List<CommandHandler<T>> handlers;
    protected final Handler defaultHandler;
    protected final EnumSet<T> enumSet;

    public BasicDispatcher(
        int workersThreadCount,
        List<CommandHandler<T>> handlers,
        Handler defaultHandler,
        Class<T> enumClass
    ) {
        checkArgument(!handlers.isEmpty());
        this.handlers = handlers;
        this.router = new Router(workersThreadCount);
        this.defaultHandler = defaultHandler;
        this.enumSet = EnumSet.allOf(enumClass);//TODO Consider only supported subset (from handlers)
    }

    /**
     * Update dispatcher
     * Invokes handler by command by default
     * <p>
     * Meant to be overriden
     */
    @Override
    public void dispatch(Update update) {
        T context = findContext(update);
        for (CommandHandler<T> handler : handlers) {
            if (handler.getCommand() == context) {
                router.route(getQualifier(update), () -> handler.handleUpdate(update));
                return;
            }
        }
        //if no context at all
        router.route(getQualifier(update), () -> defaultHandler.handleUpdate(update));
    }

    /**
     * Helper logic of extracting context from raw text
     */
    @Nullable
    public final T findContextFromText(String text) {
        Matcher matcher = COMMAND_PATTERN.matcher(text);
        if (matcher.matches()) {
            String command = matcher.group(1);
            for (T t : enumSet) {
                if ((t.getValue()).equals(command)) {
                    return t;
                }
            }
        }

        return null;
    }

    /**
     * Main logic of extracting context. May be overriden
     */
    @Nullable
    protected T findContext(Update update) {
        String contextRawValue = null;
        if (update.message != null && update.message.text != null) {
            contextRawValue = update.message.text;
        }
        if (contextRawValue != null) {
            return findContextFromText(contextRawValue);
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
