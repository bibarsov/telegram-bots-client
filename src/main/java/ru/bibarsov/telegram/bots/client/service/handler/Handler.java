package ru.bibarsov.telegram.bots.client.service.handler;

import ru.bibarsov.telegram.bots.client.dto.CallbackQuery;
import ru.bibarsov.telegram.bots.client.dto.InlineQuery;
import ru.bibarsov.telegram.bots.client.dto.Message;
import ru.bibarsov.telegram.bots.client.dto.Update;

public class Handler {

    /*
        From general to specific
     */
    public final void handleUpdate(Update update) {
        handle(update);
        if (update.message != null) {
            handleMessage(update.message);
            switch (update.message.chat.type) {
                case PRIVATE:
                    handlePrivateMessage(update.message);
                    break;
                case GROUP:
                    handleGroupMessage(update.message);
                    break;
                case SUPERGROUP:
                    handleSuperGroupMessage(update.message);
                    break;
                case CHANNEL:
                    handleChannelMessage(update.message);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + update.message.chat.type);
            }
            if (update.message.newChatMembers != null) {
                handleNewChatMembers(update.message);
            }
        }
        if (update.callbackQuery != null) {
            handleCallbackQuery(update.callbackQuery);
            if (update.callbackQuery.data != null) {
                handleCallbackQueryData(update.callbackQuery);
            }
        }
        if (update.inlineQuery != null) {
            handleInlineQuery(update.inlineQuery);
        }
    }

    public void handleInlineQuery(InlineQuery inlineQuery) {
        //ignore by default
    }

    public void handleCallbackQueryData(CallbackQuery callbackQuery) {
        //ignore by default
    }

    public void handle(Update update) {
        //ignore by default
    }

    public void handleMessage(Message message) {
        //ignore by default
    }

    public void handlePrivateMessage(Message message) {
        //ignore by default
    }

    public void handleGroupMessage(Message message) {
        //ignore by default
    }

    public void handleSuperGroupMessage(Message message) {
        //ignore by default
    }

    public void handleChannelMessage(Message message) {
        //ignore by default
    }

    public void handleCallbackQuery(CallbackQuery callbackQuery) {
        //ignore by default
    }

    public void handleNewChatMembers(Message message) {
        //ignore by default
    }
}
