package com.blazingumbra.athena.discord.commands;

import com.blazingumbra.athena.discord.abstrakt.AbstractDiscordCommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;

public class AbstractDiscordCommandRaw extends AbstractDiscordCommand {
    public AbstractDiscordCommandRaw(String module, String command, String parameters) {
        super(module, command, parameters);
    }

    public AbstractDiscordCommandRaw(String module, String command) {
        super(module, command);
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
