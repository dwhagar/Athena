package new_LookupBot.main;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.Arrays;
import java.util.Optional;

public class Listener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message aMessage = event.getMessage();
        String messageContent = aMessage.getContentRaw();

        if (event.getAuthor().isBot())
            return;
        Optional<ECommands> optionalCommand = Arrays
                .stream(ECommands.values())
                .filter(e -> messageContent.startsWith(e.getCommand()))
                .findAny();
        optionalCommand.ifPresent(e -> e.execute(event));
    }
}
