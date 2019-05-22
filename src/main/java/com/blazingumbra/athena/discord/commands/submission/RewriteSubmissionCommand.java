package com.blazingumbra.athena.discord.commands.submission;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;

public class RewriteSubmissionCommand extends SubmissionCommand {
    private String parameter;
    public RewriteSubmissionCommand(String parameter) {
        command = "rewrite";
        this.parameter = parameter;
    }

    @Override
    public boolean checkPermission(Permission[] permissions, Member member) {
        return false;
    }

    @Override
    public boolean execute(TextChannel textChannel) {
        return false;
    }
}
