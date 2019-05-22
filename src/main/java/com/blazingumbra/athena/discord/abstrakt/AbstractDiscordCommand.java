package com.blazingumbra.athena.discord.abstrakt;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;

public abstract class AbstractDiscordCommand {
    protected String module;
    protected String command;
    protected String parameters;

    public abstract boolean checkPermission(Permission[] permissions, Member member);

    public abstract boolean execute(TextChannel textChannel);
}
