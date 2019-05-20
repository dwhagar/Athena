package com.blazingumbra.athena.discord.commands.wiki;

import com.blazingumbra.athena.discord.abstrakt.AbstractDiscordCommand;

public class WikiCommand extends AbstractDiscordCommand {
    public String command;
    String parameter;

    WikiCommand() {
        module = "wiki";
    }
}
