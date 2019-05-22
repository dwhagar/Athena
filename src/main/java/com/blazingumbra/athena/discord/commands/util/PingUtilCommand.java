package com.blazingumbra.athena.discord.commands.util;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;

public class PingUtilCommand extends UtilCommand {
    public PingUtilCommand() {
        module = "util";
        command = "ping";
    }

    @Override
    public boolean checkPermission(Permission[] permissions, Member member) {
        return false;
    }

    @Override
    public boolean execute(TextChannel textChannel) {
        return false;
    }
}
