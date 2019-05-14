package new_LookupBot.main;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import new_LookupBot.exception.LoginFailedException;
import new_LookupBot.objects.CharacterSheet;
import new_LookupBot.objects.CsrfTokenResponse;
import new_LookupBot.objects.EditResponse;
import new_LookupBot.out.DiscordWriter;
import new_LookupBot.wiki.WikiInteractor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.Optional;

public class RequestHandler {
    private static ObservableMap<String, InternalSubmission> submissions = FXCollections.emptyObservableMap();

    public static void init()
    {
        submissions.addListener((MapChangeListener<String, InternalSubmission>) change -> {
            ObservableMap<? extends String, ? extends InternalSubmission> map = change.getMap();
            Gson gson = new Gson();
            String mapAsJson = gson.toJson(map);
            Path file = null;
            try {
                file = Files.createFile(Paths.get("submissionmap.json"));
                BufferedWriter writer = Files.newBufferedWriter(file, StandardOpenOption.WRITE);
                writer.write(mapAsJson);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public static void approveSubmission(String id) throws Exception {
        //TODO check if approver has staff role.
        InternalSubmission submission = submissions.get(id);
        String wikiURL = submission.getWikiUrl();
        WikiInteractor interactor = WikiInteractor.getInstance("https://home.blazingumbra.com/w");
        String pageText = interactor.getPageText(id);
        String approvedPageText = pageText.replace("Unapproved", "Approved");
        CsrfTokenResponse csrfTokenResponse = interactor.retrieveCsrfToken();
        EditResponse editResponse = interactor.editPageByID(id, approvedPageText, csrfTokenResponse.getToken());
        String message;
        JDA jda = Main.getTheJDA();
        Guild aGuild = jda.getGuildById(230687291071266816L);
        Optional<TextChannel> textChannel = aGuild.getTextChannelsByName("electric-dreams", true).stream().findFirst();
        if (!textChannel.isPresent()) {
            throw new Exception("Cannot find channel to notify of submission.");
        }
        if(editResponse.hasSucceeded()) {
            message = "Your submission was successfully updated.";
            submissions.remove(id);
        }
        else
        {
            message = "Edit did not succeed.";
        }
        DiscordWriter.sendMessage(textChannel.get(), message);
    }
    public static void rewriteSubmmission(String id, String player, CharacterSheet aSheet) throws Exception {
        InternalSubmission submission = submissions.get(id);
        if (!submission.getPlayerID().equals(player)) {
            throw new Exception("Wrong player");
        }
        try
        {
            WikiInteractor interactor = WikiInteractor.getInstance("https://home.blazingumbra.com/w");
            CsrfTokenResponse csrfTokenResponse = interactor.retrieveCsrfToken();
            EditResponse editResponse = interactor.editPageByID(id, aSheet.toPage(),csrfTokenResponse.getToken());
            if(editResponse.hasSucceeded())
            {
                JDA jda = Main.getTheJDA();
                Guild aGuild = jda.getGuildById(230687291071266816L);
                Optional<TextChannel> textChannel = aGuild.getTextChannelsByName("electric-dreams", true).stream().findFirst();
                if (!textChannel.isPresent()) {
                    throw new Exception("Cannot find channel to notify of submission.");
                }

                DiscordWriter.sendMessage(
                        textChannel.get(),
                        "Your submission was successfully updated."
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LoginFailedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void createSubmission(EditResponse er, String playerID) throws Exception {
        String pageID = er.getPageid();
        String revID = er.getRevID();
        JDA jda = Main.getTheJDA();
        Guild aGuild = jda.getGuildById(230687291071266816L);
        Optional<TextChannel> textChannel = aGuild.getTextChannelsByName("electric-dreams", true).stream().findFirst();
        if (!textChannel.isPresent()) {
            throw new Exception("Cannot find channel to notify of submission.");
        }
        String link = "https://home.blazingumbra.com/w/" +er.getTitle();
        submissions.put(pageID, new InternalSubmission(link, playerID, revID));
        DiscordWriter.sendMessage(
                textChannel.get(),
        "A new request has been submitted.\n" +
                    "Submission ID: " + pageID + " \n" +
                    "Link: " + link
        );
    }
}
