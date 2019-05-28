package com.blazingumbra.athena.discord.commands.submission;

import com.blazingumbra.athena.discord.abstrakt.AbstractDiscordCommand;

abstract class SubmissionCommand extends AbstractDiscordCommand {
    public SubmissionCommand(String module, String command, String parameters) {
        super(module, command, parameters);
    }
}
