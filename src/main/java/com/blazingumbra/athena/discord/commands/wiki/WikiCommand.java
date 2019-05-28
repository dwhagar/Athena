package com.blazingumbra.athena.discord.commands.wiki;

import com.blazingumbra.athena.discord.abstrakt.AbstractDiscordCommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;

abstract class WikiCommand extends AbstractDiscordCommand {
    WikiCommand(String parameters) {
        super(parameters);
    }

    @Override
    public boolean checkPermission(Member member, Permission... permissions) {
        return true;
    }
}
