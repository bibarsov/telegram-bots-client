## Simple Telegram Bots client for Java

#### Basic usage ####

1. Define your own enum of commands
2. Define your handler for every command
3. Provide the stuff above and `botApiKey` to `UpdatePollerService`
4. Call `UpdatePollerService.doJob();`
5. ...
6. PROFIT!

Advanced users may override default logic without big efforts.

#### TL;DR ####

**Example bot using this client** -> [ExampleBot](https://github.com/bibarsov/telegram-bots-example).