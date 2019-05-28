package com.blazingumbra.athena.discord.commands.admin;

import com.blazingumbra.athena.discord.BotContainer;
import net.dv8tion.jda.core.entities.TextChannel;

public class ShutdownAdministratorCommand extends AdministratorCommand {
    public ShutdownAdministratorCommand(String module, String command) {
        super(module, command);
    }

    @Override
    public boolean execute(TextChannel textChannel) {
        BotContainer.getJda().shutdown();
        return true;
    }
}
