package com.blazingumbra.athena.discord.commands.submission;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;

public class RewriteSubmissionCommand extends SubmissionCommand {
    public RewriteSubmissionCommand(String parameters) {
        super(parameters);
    }

    @Override
    public boolean checkPermission(Member member, Permission... permissions) {
        return false;
    }

    @Override
    public boolean execute(TextChannel textChannel) {
        return false;
    }
}
