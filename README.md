## Simple Telegram Bots client for Java

#### Usage:

**1. Define your own enum of commands**
```java
public enum Command {

    START
}
```
**2. Define your handler for every command**
```java
public class StartHandler extends Handler<Command> {

    private final MessageService messageService;

    public StartHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void handle(Message message) {
        messageService.scheduleMessage(message.from.id, "Hello.");
    }

    @Override
    public Command getCommand() {
        return Command.START;
    }
}
```

**3. Now just initialize your bot with _botApiKey_ like following:**

```java
        //service for message sending
        MessageService messageService = new MessageService(botApiKey);

        //command handlers
        Handler<Command> startHandler = new StartHandler(messageService);

        UpdatePollerService pollerService = new UpdatePollerService(
                botApiKey,
                10, //Number of processing threads
                Command.class, //enum of commands
                startHandler
        );
        pollerService.doJob();
```

Advanced users may override default logic without big efforts.
