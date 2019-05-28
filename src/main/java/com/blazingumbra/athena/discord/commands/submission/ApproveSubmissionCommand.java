package com.blazingumbra.athena.discord.commands.submission;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;

import java.util.Arrays;
import java.util.List;

public class ApproveSubmissionCommand extends SubmissionCommand {
    public ApproveSubmissionCommand(String parameter) {
        super(parameter);
    }

    @Override
    public boolean checkPermission(Member member, Permission... permissions) {
        List<Permission> memberPermissions = member.getPermissions();
        return memberPermissions.containsAll(Arrays.asList(permissions));
    }

    @Override
    public boolean execute(TextChannel textChannel) {
        return true;
    }
}
