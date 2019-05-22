package com.blazingumbra.athena.discord.commands;

import com.blazingumbra.athena.discord.abstrakt.AbstractDiscordCommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;

public class AbstractDiscordCommandRaw extends AbstractDiscordCommand {
    @Override
    public boolean checkPermission(Permission[] permissions, Member member) {
        return false;
    }

    @Override
    public boolean execute(TextChannel textChannel) {
        return false;
    }
}