package com.blazingumbra.athena.discord.commands.admin;

import com.blazingumbra.athena.discord.abstrakt.AbstractDiscordCommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;

import java.util.Arrays;
import java.util.List;

public class AdministratorCommand extends AbstractDiscordCommand {
    public AdministratorCommand(String module, String command) {
        super(module, command);
    }

    @Override
    public boolean checkPermission(Member member, Permission... permissions) {
        List<Permission> memberPermission = member.getPermissions();
        return memberPermission.containsAll(Arrays.asList(permissions));
    }

    @Override
    public boolean execute(TextChannel textChannel) {
        return false;
    }
}
