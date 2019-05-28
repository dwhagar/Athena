package com.blazingumbra.athena.discord.commands.util;

import com.blazingumbra.athena.discord.abstrakt.AbstractDiscordCommand;

abstract class UtilCommand extends AbstractDiscordCommand {
    public UtilCommand(String module, String command) {
        super(module, command);
    }
}
