package com.blazingumbra.athena.discord.commands.submission;

public class RewriteSubmissionCommand extends SubmissionCommand {
    public RewriteSubmissionCommand(String parameter) {
        command = "rewrite";
        this.parameter = parameter;
    }
}
