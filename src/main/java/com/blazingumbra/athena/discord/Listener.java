package com.blazingumbra.athena.discord;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Listener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message aMessage = event.getMessage();

        if (BotContainer.commandHandler.isBot(event.getAuthor())) {
            return;
        }
    }
}
