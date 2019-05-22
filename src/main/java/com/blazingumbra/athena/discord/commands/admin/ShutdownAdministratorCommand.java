package com.blazingumbra.athena.discord.commands.admin;

import net.dv8tion.jda.core.entities.TextChannel;

public class ShutdownAdministratorCommand extends AdministratorCommand {
    public ShutdownAdministratorCommand() {
        module = "admin";
        command = "shutdown";
    }

    @Override
    public boolean execute(TextChannel textChannel) {
        return false;
    }
}
