package com.blazingumbra.athena.discord.commands.wiki;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;

public class LookupWikiCommand extends WikiCommand {
    private String parameter;
    public LookupWikiCommand(String parameter) {
        module = "wiki";
        command = "lookup";
        this.parameter =  parameter;
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
