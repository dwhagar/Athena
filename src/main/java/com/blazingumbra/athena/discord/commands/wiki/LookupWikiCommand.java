package com.blazingumbra.athena.discord.commands.wiki;

import com.blazingumbra.athena.wiki.exceptions.LoginFailedException;
import com.blazingumbra.athena.wiki.interaction.SearchWikiInteraction;
import com.blazingumbra.athena.wiki.interaction.WikiAuthenticator;
import com.blazingumbra.athena.wiki.interaction.WikiSession;
import net.dv8tion.jda.core.entities.TextChannel;
import org.json.JSONArray;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class LookupWikiCommand extends WikiCommand {
    public LookupWikiCommand(String parameter) {
        super(parameter);
        module = "wiki";
        command = "lookup";
    }

    @Override
    public boolean execute(TextChannel textChannel) {
        boolean exectionSuccess = true;
        try {
            WikiSession wikiSession = WikiAuthenticator.login("Neo Akazuli@Script's_Wiki_Interaction_Tool", "f7uglcs7a7rjghjpvmv8fjbeh4vi6dkb");

            String s = SearchWikiInteraction.execute(wikiSession, getParameters());

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

            textChannel.sendMessage(response.toString()).queue();
        } catch (IOException | LoginFailedException | URISyntaxException e) {
            logger.error(e.getMessage());
            exectionSuccess = false;
        }
        return exectionSuccess;
    }
}
