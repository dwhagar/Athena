package com.blazingumbra.athena.discord.commands.admin;

import net.dv8tion.jda.core.entities.TextChannel;

public class InviteAdministratorCommand extends AdministratorCommand {
    public InviteAdministratorCommand() {
        module = "admin";
        command = "invite";
    }

    @Override
    public boolean execute(TextChannel textChannel) {
        return false;
    }
}
