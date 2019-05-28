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
    public boolean checkPermission(Member member, Permission... permissions) {
        return true;
    }

    @Override
    public boolean execute(TextChannel textChannel) {
        logger.info("Caught Ping command.");
        textChannel.sendMessage("Pong!").queue();
        return true;
    }
}
