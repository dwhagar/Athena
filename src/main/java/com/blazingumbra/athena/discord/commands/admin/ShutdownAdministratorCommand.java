package com.blazingumbra.athena.discord.commands.admin;

import com.blazingumbra.athena.discord.BotContainer;
import net.dv8tion.jda.core.entities.TextChannel;

public class ShutdownAdministratorCommand extends AdministratorCommand {
    public ShutdownAdministratorCommand()
    {
        super();
    }

    @Override
    public boolean execute(TextChannel textChannel) {
        textChannel.sendMessage("Shutting down...").complete();
        BotContainer.getJda().shutdown();
        return true;
    }
}
