package com.blazingumbra.athena.discord.commands.wiki;

import com.blazingumbra.athena.discord.abstrakt.AbstractDiscordCommand;

abstract class WikiCommand extends AbstractDiscordCommand {
    public WikiCommand(String module, String command, String parameters) {
        super(module, command, parameters);
    }
}
