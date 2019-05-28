package com.blazingumbra.athena.discord;

import com.blazingumbra.athena.discord.commands.admin.InviteAdministratorCommand;
import com.blazingumbra.athena.discord.commands.admin.ShutdownAdministratorCommand;
import com.blazingumbra.athena.discord.commands.submission.ApproveSubmissionCommand;
import com.blazingumbra.athena.discord.commands.submission.RewriteSubmissionCommand;
import com.blazingumbra.athena.discord.commands.util.HelpUtilCommand;
import com.blazingumbra.athena.discord.commands.util.PingUtilCommand;
import com.blazingumbra.athena.discord.commands.wiki.LookupWikiCommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Listener extends ListenerAdapter {
    private Logger logger = LoggerFactory.getLogger(Listener.class);
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message aMessage;
        String rawMessage;
        String[] bufferMessage;

        aMessage = event.getMessage();
        rawMessage = aMessage.getContentRaw();

        if (!rawMessage.startsWith("!!")) {
            return;
        }

        if (event.getAuthor().isBot()) {
            logger.info("Message " +rawMessage+" by " + event.getAuthor().getName() + " would've triggered, but Author was a bot.");
            return;
        }

        rawMessage = rawMessage.replace("!!", "");
        bufferMessage = rawMessage.split(" ");
        String module;
        String command;
        String parameter = "";

        if(bufferMessage.length == 3)
        {
            module = bufferMessage[0];
            command = bufferMessage[1];
            parameter = bufferMessage[2];
        }
        else if(bufferMessage.length == 2)
        {
            module = bufferMessage[0];
            command = bufferMessage[1];
        }
        else
        {
            event.getTextChannel().sendMessage("Wrong command format.").queue();
            return;
        }

        TextChannel tc = event.getTextChannel();

        if(module.equals("admin"))
        {
            if (command.equals("shutdown"))
            {
                ShutdownAdministratorCommand command1 = new ShutdownAdministratorCommand();
                if(command1.checkPermission(event.getMember(), Permission.ADMINISTRATOR))
                {
                    command1.execute(tc);
                }
                else
                {
                    tc.sendMessage("Insufficient permissions.").queue();
                    logger.info(event.getAuthor().getName()+ " had insufficient permissions for executing " + ShutdownAdministratorCommand.class.getName());
                }
            }
            else if(command.equals("invite"))
            {
                InviteAdministratorCommand command1 = new InviteAdministratorCommand();
                if(command1.checkPermission(event.getMember(), Permission.ADMINISTRATOR))
                {
                    command1.execute(tc);
                }
                else
                {
                    tc.sendMessage("Insufficient permissions.").queue();
                    logger.info(event.getAuthor().getName()+ " had insufficient permissions for executing " + InviteAdministratorCommand.class.getName());
                }
            }
        }
        else if (module.equals("util"))
        {
            if(command.equals("ping"))
            {
                PingUtilCommand command1 = new PingUtilCommand();
                command1.execute(tc);
            }
            if(command.equals("help"))
            {
                HelpUtilCommand command1 = new HelpUtilCommand();
                command1.execute(tc);
            }
        }
        else if(module.equals("submission") && (parameter != null))
        {
            if(command.equals("approve"))
            {
                ApproveSubmissionCommand command1 = new ApproveSubmissionCommand(parameter);
                if(command1.checkPermission(event.getMember(), Permission.ADMINISTRATOR))
                {
                    command1.execute(tc);
                }
                else
                {
                    tc.sendMessage("Insufficient permissions.").queue();
                    logger.info(event.getAuthor().getName()+ " had insufficient permissions for executing " + ApproveSubmissionCommand.class.getName());
                }
            }
            else if(command.equals("rewrite"))
            {
                RewriteSubmissionCommand command1 = new RewriteSubmissionCommand(parameter);
                command1.execute(tc);
            }
        }
        else if(module.equals("wiki") && (parameter != null))
        {
            if(command.equals("lookup"))
            {
                LookupWikiCommand command1 = new LookupWikiCommand(parameter);
                command1.execute(tc);
            }
        }
    }
}
