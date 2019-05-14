package new_LookupBot.out;

import net.dv8tion.jda.core.entities.TextChannel;

public class DiscordWriter {
    public static void sendMessage(TextChannel aChannel, String message)
    {
        aChannel.sendMessage(message).queue();
    }

    public static String sendreturnMessage(TextChannel aChannel, String message)
    {
        return aChannel.sendMessage(message).complete().getId();
    }
}
