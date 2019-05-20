package com.blazingumbra.athena.discord.commands.admin;

import com.blazingumbra.athena.discord.abstrakt.AbstractDiscordCommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;

import java.util.Arrays;
import java.util.List;

public class AdministratorCommand extends AbstractDiscordCommand {
    public String command;

    AdministratorCommand() {
        module = "admin";
    }

    @Override
    public boolean checkPermission(Permission[] permissions, Member member) {
        List<Permission> memberPermission = member.getPermissions();
        boolean hasRoles = memberPermission.containsAll(Arrays.asList(permissions));
        return hasRoles;
    }
}
