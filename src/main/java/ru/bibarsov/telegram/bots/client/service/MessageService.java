package ru.bibarsov.telegram.bots.client.service;

import ru.bibarsov.telegram.bots.client.dto.*;
import ru.bibarsov.telegram.bots.client.repository.client.TelegramBotApi;
import ru.bibarsov.telegram.bots.client.value.ParseMode;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@ParametersAreNonnullByDefault
public class MessageService {

    private final ExecutorService executors = Executors.newCachedThreadPool();
    private final TelegramBotApi telegramBotApi;

    public MessageService(TelegramBotApi telegramBotApi) {
        this.telegramBotApi = telegramBotApi;
    }

    public void scheduleMessage(String id, String text) {
        scheduleMessage(new SendMessageRequest(id, text, null, null, null, null));
    }

    public void scheduleMessage(long id, String text) {
        scheduleMessage(new SendMessageRequest(String.valueOf(id), text, null, null, null, null));
    }

    public void scheduleMessage(String id, String text, ParseMode parseMode) {
        scheduleMessage(new SendMessageRequest(id, text, parseMode.value, null, null, null));
    }

    public void scheduleMessage(long id, String text, ParseMode parseMode) {
        scheduleMessage(new SendMessageRequest(String.valueOf(id), text, parseMode.value, null, null, null));
    }

    public void scheduleMessage(String id, String text, ParseMode parseMode, boolean disableWebPagePreview) {
        scheduleMessage(new SendMessageRequest(id, text, parseMode.value, disableWebPagePreview, null, null));
    }

    public void scheduleMessage(long id, String text, ParseMode parseMode, boolean disableWebPagePreview) {
        scheduleMessage(new SendMessageRequest(String.valueOf(id), text, parseMode.value, disableWebPagePreview, null, null));
    }

    public void scheduleMessage(String id, String text, ParseMode parseMode, boolean disableWebPagePreview, boolean disableNotification) {
        scheduleMessage(new SendMessageRequest(id, text, parseMode.value, disableWebPagePreview, disableNotification, null));
    }

    public void scheduleMessage(long id, String text, ParseMode parseMode, boolean disableWebPagePreview, boolean disableNotification) {
        scheduleMessage(new SendMessageRequest(String.valueOf(id), text, parseMode.value, disableWebPagePreview, disableNotification, null));
    }

    public void scheduleMessage(String id, String text, boolean disableWebPagePreview) {
        scheduleMessage(new SendMessageRequest(id, text, null, disableWebPagePreview, null, null));
    }

    public void scheduleMessage(long id, String text, boolean disableWebPagePreview) {
        scheduleMessage(new SendMessageRequest(String.valueOf(id), text, null, disableWebPagePreview, null, null));
    }

    public void scheduleMessage(String id, String text, boolean disableWebPagePreview, boolean disableNotification) {
        scheduleMessage(new SendMessageRequest(id, text, null, disableWebPagePreview, disableNotification, null));
    }

    public void scheduleMessage(long id, String text, boolean disableWebPagePreview, boolean disableNotification) {
        scheduleMessage(new SendMessageRequest(String.valueOf(id), text, null, disableWebPagePreview, disableNotification, null));
    }

    public void scheduleMessage(SendMessageRequest request) {
        scheduleMessage(request, (ignored) -> {
        });
    }

    public void scheduleEditMessageText(EditMessageTextRequest request) {
        scheduleEditMessageText(request, (ignored) -> {
        });
    }

    public void scheduleMessage(SendMessageRequest request, Consumer<Message> consumer) {
        executors.submit(() -> consumer.accept(telegramBotApi.sendMessage(request)));
    }

    public void scheduleEditMessageText(EditMessageTextRequest request, Consumer<Message> consumer) {
        executors.submit(() -> consumer.accept(telegramBotApi.editMessageText(request)));
    }

    public void schedulePhoto(SendPhotoRequest request) {
        executors.submit(() -> telegramBotApi.sendPhoto(request));
    }

    public void scheduleAnswerInlineQuery(InlineQueryAnswer answer) {
        executors.submit(() -> telegramBotApi.answerInlineQuery(answer));
    }

    public void scheduleAnswerCallbackQuery(CallbackQueryAnswer answer) {
        executors.submit(() -> telegramBotApi.answerCallbackQuery(answer));
    }
}
