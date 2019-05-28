package com.blazingumbra.athena.discord.commands.admin;

import com.blazingumbra.athena.discord.BotContainer;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.TextChannel;

public class InviteAdministratorCommand extends AdministratorCommand {

    public InviteAdministratorCommand(String module, String command) {
        super(module, command);
    }

    @Override
    public boolean execute(TextChannel textChannel) {
        String inviteUrl = BotContainer.getJda().asBot().getInviteUrl(Permission.ADMINISTRATOR);
        textChannel.sendMessage("Invite me with this url:\n" + inviteUrl).queue();
        return true;
    }
}
