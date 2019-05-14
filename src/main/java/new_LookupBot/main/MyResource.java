package new_LookupBot.main;

import new_LookupBot.exception.EditFailedException;
import new_LookupBot.objects.CharacterSheet;
import new_LookupBot.objects.CsrfTokenResponse;
import new_LookupBot.objects.EditResponse;
import new_LookupBot.objects.LoginTokenResponse;
import new_LookupBot.wiki.WikiInteractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URLEncoder;

@Path("makewiki")
public class MyResource {
    private static Logger logger = LoggerFactory.getLogger(MyResource.class);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doWork(CharacterSheet aSheet) {
        Response response = Response.status(500).build();
        WikiInteractor interactor = null;
        try{
            interactor = WikiInteractor.getInstance("https://home.blazingumbra.com");
            CsrfTokenResponse csrfTokenResponse = interactor.retrieveCsrfToken();
            String csrfToken = csrfTokenResponse.getToken();
            String sheetLiteral = aSheet.toPage();
            String title = aSheet.getCharacterName();

            EditResponse er = interactor.createPage(title, sheetLiteral, csrfToken);
            if(!er.hasSucceeded()) {
                throw new EditFailedException("Creating page failed.");
            }
            RequestHandler.createSubmission(er, aSheet.getPlayerID());
        } catch (Exception e) {
            logger.error(e.getMessage());
            response = Response.status(500).entity(e.getMessage()).build();
        }
        finally {
            interactor.close();
        }
        return response;
    }
}
