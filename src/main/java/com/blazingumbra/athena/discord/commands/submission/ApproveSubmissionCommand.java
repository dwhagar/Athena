package com.blazingumbra.athena.discord.commands.submission;

public class ApproveSubmissionCommand extends SubmissionCommand {
    public ApproveSubmissionCommand(String parameter) {
        command = "approve";
        this.parameter = parameter;
    }
}
