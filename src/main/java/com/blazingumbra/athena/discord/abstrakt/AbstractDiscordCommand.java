package com.blazingumbra.athena.discord.abstrakt;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDiscordCommand {
    protected String module;
    protected String command;
    protected String parameters;
    protected Logger logger = LoggerFactory.getLogger("athena.command");

    public AbstractDiscordCommand(String module, String command, String parameters) {
        this.module = module;
        this.command = command;
        this.parameters = parameters;
    }

    public AbstractDiscordCommand(String module, String command) {
        this.module = module;
        this.command = command;
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
