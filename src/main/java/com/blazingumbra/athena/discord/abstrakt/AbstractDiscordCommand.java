package com.blazingumbra.athena.discord.abstrakt;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;

public abstract class AbstractDiscordCommand {
    protected String module;

    public abstract boolean checkPermission(Permission[] permissions, Member member);

    //TODO Implement execute of command
}
