package com.blazingumbra.athena.discord.abstrakt;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDiscordCommand {
    protected String module;
    protected String command;
    private String parameters;
    protected Logger logger = LoggerFactory.getLogger("athena.command");

    public AbstractDiscordCommand() {
    }

    public AbstractDiscordCommand(String parameters) {
        this.parameters = parameters;
    }

    public abstract boolean checkPermission(Member member, Permission... permissions);

    public abstract boolean execute(TextChannel textChannel);

    public String getModule() {
        return module;
    }

    public String getCommand() {
        return command;
    }

    public String getParameters() {
        return parameters;
    }
}
