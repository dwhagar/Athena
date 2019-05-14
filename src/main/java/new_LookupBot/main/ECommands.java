package new_LookupBot.main;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import new_LookupBot.exception.LoginFailedException;
import new_LookupBot.out.DiscordWriter;
import new_LookupBot.wiki.WikiInteractor;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum ECommands {
    LOOKUP("!!lookup", "Returns a list of wikipages matching Name. Usage: !!lookup Name")   {
        @Override
        void execute(MessageReceivedEvent event) {
            WikiInteractor interactor = null;
            try {
                interactor = WikiInteractor.getInstance("https://home.blazingumbra.com/wiki");
            } catch (LoginFailedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            logger.info("Caught " + this.getCommand() + " Command called by user " + event.getAuthor().getName());
            String messageContent = event.getMessage().getContentRaw();
            String query = messageContent.substring(9);
            String s;
            try {
                s = interactor.actionsearch(query);
                s = jsonToString(s);
                DiscordWriter.sendMessage(event.getTextChannel(), s);
            }catch (IOException e) {
                logger.error(e.toString());
            }
        }
    },
    PING("!!ping", "Pings you.") {
        @Override
        void execute(MessageReceivedEvent event) {
            logger.info("Caught " + this.getCommand() + " Command called by user " + event.getAuthor().getName());
            DiscordWriter.sendMessage(event.getTextChannel(), "Pong!");
        }
    },
    INVITE("!!invite", "Drops you an invite link to invite me to your guild. Due to security reasons, this is only available for devs.") {
        @Override
        void execute(MessageReceivedEvent event) {
            logger.info("Caught " + this.getCommand() + " Command called by user " + event.getAuthor().getName());
            if (event.getMember().getUser().getIdLong() == myself) {
                String inviteUrl = event.getJDA().asBot().getInviteUrl(Permission.ADMINISTRATOR);
                DiscordWriter.sendMessage(event.getTextChannel(), inviteUrl);
            }
        }
    },
    SHUTDOWN("!!shutdown", "Admin Command: Shuts the bot down.") {
        @SuppressWarnings("SpellCheckingInspection")
        @Override
        void execute(MessageReceivedEvent event) {
            logger.info("Caught " + this.getCommand() + " Command called by user " + event.getAuthor().getName());
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)
                    || (event.getMember().getUser().getIdLong() == myself)) {
                logger.warn("Shutdown commencing.");
                event.getTextChannel().sendMessage("Yo dawg, you are such a turn off.").complete();
                event.getJDA().shutdown();
            } else {
                DiscordWriter.sendMessage(event.getTextChannel(), "You are not my boss!");
            }
        }
    },
    COOKIE("!!cookie", "Rewards the bot, 'cause it's a good boi.") {
        @Override
        void execute(MessageReceivedEvent event) {
            event.getMessage().addReaction("🍪").queue();
            String nick = event.getMember().getNickname();
            if(nick == null)
                nick = event.getMember().getEffectiveName();
            DiscordWriter.sendMessage(event.getTextChannel(), "Aw, that's kind. Thank you, "+ nick + "! :heart:");
        }
    },
    JUDGE("!!judge", "By public demand, judges.") {
        @Override
        void execute(MessageReceivedEvent event) {
            List<String> quoteList;
            {
                quoteList = new ArrayList<>();
                quoteList.add("You some special snowflake.");
                quoteList.add("*silently shakes head and facepalms.*");
                quoteList.add("*takes off glasses* Holy mother of ~~Lance Thomas~~ god.");
                quoteList.add("You sail through the air like an eagle. A fat eagle.");
                quoteList.add("You're like pineapple on pizza. *drops mic*");
                quoteList.add("*eyes*. Are you my server? 'cause I'd like to ping you.. ;)");
                quoteList.add("*looks down.* *looks up.* Nope.");
                quoteList.add("You are sufficient evidence for the theory of evolution. No creator would intentionally make such a huge dumbass.");
                quoteList.add("*Scrubness +1*: Congratulations! You've leveled up. You are now: Scrub Baron.");
            }
            Random random = new Random();
            int randomNum = random.nextInt(quoteList.size()-1);
            TextChannel tc = event.getTextChannel();
            DiscordWriter.sendMessage(tc, quoteList.get(randomNum));
        }
    },
    SUBMISSION_REWRITE("!!submission rewrite", "Gets the whole submission from the wiki for editing")
            {
                @Override
                void execute(MessageReceivedEvent event) {

                }
            },
    SUBMISSION_APPROVE("!!submission approve", "Approves a pending submission.") {
        @Override
        void execute(MessageReceivedEvent event) {
            String message = event.getMessage().getContentRaw();
            String submissionID = message.replace(this.getCommand(), "");
            submissionID = submissionID.trim();
            try {
                RequestHandler.approveSubmission(submissionID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    },

    HELP("!!help", "Displays this text.") {
        @Override
        void execute(MessageReceivedEvent event) {
            StringBuilder text = new StringBuilder();
            text.append("```");
            for (ECommands e : ECommands.values()) {
                text.append(e.getCommand())
                        .append("   ")
                        .append(e.getHelpText())
                        .append("\n");
            }
            text.append("```");
            DiscordWriter.sendMessage(event.getTextChannel(), text.toString());
        }
    };

    private static Long myself = Long.parseLong("106817624091947008");
    private static Logger logger = LoggerFactory.getLogger(ECommands.class);
    private String command;
    private String helpText;
    ECommands(String command, String helpText) {
        this.command = command;
        this.helpText = helpText;
    }

    abstract void execute(MessageReceivedEvent event);

    public String getCommand() {
        return command;
    }

    public String getHelpText() {
        return helpText;
    }

    public static String jsonToString(String s) {
        StringBuilder response = new StringBuilder();
        JSONArray json = new JSONArray(s);
        String searchTerm = json.getString(0);
        List<Object> titles = json.getJSONArray(1).toList();
        List<Object> urls = json.getJSONArray(3).toList();
        response.append("You've searched for **")
                .append(searchTerm)
                .append("**! I found ")
                .append(titles.size())
                .append(" result(s). \n");
        int i = 0;
        for (Object obj : titles) {
            response.append("For:**")
                    .append(titles.get(i))
                    .append("** I found: <")
                    .append(urls.get(i))
                    .append(">\n");
            i++;
        }
        return response.toString();
    }
}
