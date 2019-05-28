package com.blazingumbra.athena.discord.commands.submission;

import com.blazingumbra.athena.discord.abstrakt.AbstractDiscordCommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;

abstract class SubmissionCommand extends AbstractDiscordCommand {
    SubmissionCommand(String parameters) {
        super(parameters);
    }

    @Override
    public boolean checkPermission(Member member, Permission... permissions) {
        return true;
    }

    @Override
    public boolean execute(TextChannel textChannel) {
        return true;
    }
}
