package com.blazingumbra.athena.discord.commands.util;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;

public class HelpUtilCommand extends UtilCommand {
    public HelpUtilCommand()
    {
        super();
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
