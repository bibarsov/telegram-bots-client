package ru.bibarsov.telegram.bots.client.service.handler;

import ru.bibarsov.telegram.bots.client.dto.*;

import java.util.List;

public class Handler {

    public final void handleUpdate(Update update) {
        handle(update);
        if (update.message != null) {
            handle(update.message);
            switch (update.message.chat.type) {
                case PRIVATE:
                    handlePrivateMessage(update.message);
                    break;
                case GROUP:
                    handleGroupMessage(update.message);
                    break;
                case SUPERGROUP:
                    //TODO reorganize logic of passing arguments
                    handleSuperGroupMessage(update.message);
                    break;
                case CHANNEL:
                    handleChannelMessage(update.message);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + update.message.chat.type);
            }
            if (update.message.newChatMembers != null) {
                handleNewChatMembers(update.message.newChatMembers, update.message);
            }
        }
        if (update.callbackQuery != null) {
            handle(update.callbackQuery);
        }
    }

    public void handle(Update update) {
        //ignore by default
    }

    public void handle(Message message) {
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

    public void handle(List<PhotoSize> photoSizes) {
        //ignore by default
    }

    public void handle(CallbackQuery callbackQuery) {
        //ignore by default
    }

    public void handleNewChatMembers(List<User> newChatMembers, Message message) {
        //ignore by default
    }
}
