package com.blazingumbra.athena.discord.commands.submission;

import com.blazingumbra.athena.discord.abstrakt.AbstractDiscordCommand;

public class SubmissionCommand extends AbstractDiscordCommand {
    public String command;
    String parameter;

    SubmissionCommand() {
        module = "submission";
    }
}
