package com.blazingumbra.athena.discord.commands.wiki;

public class LookupWikiCommand extends WikiCommand {
    public LookupWikiCommand(String parameter) {
        command = "lookup";
        this.parameter =  parameter;
    }
}
