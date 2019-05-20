package com.blazingumbra.athena.discord;

import com.blazingumbra.athena.discord.abstrakt.AbstractDiscordCommand;
import net.dv8tion.jda.core.entities.User;

public class DiscordCommandHandler{
    boolean isBot(User author)
    {
        return author.isBot();
    }

    public void handle(AbstractDiscordCommand command) {
    }
}
