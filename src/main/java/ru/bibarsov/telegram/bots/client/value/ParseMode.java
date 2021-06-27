package ru.bibarsov.telegram.bots.client.value;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public enum ParseMode {

    MARKDOWN("Markdown"),
    MARKDOWN_V_2("MarkdownV2"),
    HTML("HTML");

    public final String value;

    ParseMode(String value) {
        this.value = value;
    }
}
