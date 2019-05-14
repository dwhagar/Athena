package new_LookupBot.wiki;

import com.google.gson.Gson;
import new_LookupBot.exception.LoginFailedException;
import new_LookupBot.objects.*;
import new_LookupBot.util.ReaderMaker;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

public class WikiInteractor implements AutoCloseable{
    private String wikiBaseUrl;
    private String wikiApiUrl;
    private static HttpClient aClient;
    private static Gson gson;
    private static Logger logger = LoggerFactory.getLogger(WikiInteractor.class);

    private WikiInteractor(String wikiBaseUrl) throws IOException, URISyntaxException, LoginFailedException {

    }
    public static WikiInteractor getInstance(String wikiBaseUrl) throws LoginFailedException, IOException, URISyntaxException {
        WikiInteractor interactor = new WikiInteractor(wikiBaseUrl);
        interactor.wikiBaseUrl = wikiBaseUrl;
        interactor.wikiApiUrl = wikiBaseUrl+"/api.php";
        aClient = HttpClients.createDefault();
        gson = new Gson();

        LoginTokenResponse loginTokenResponse = interactor.retrieveLoginToken();
        interactor.login("Neo Akazuli@Script's_Wiki_Interaction_Tool", "f7uglcs7a7rjghjpvmv8fjbeh4vi6dkb", loginTokenResponse.getToken());
        return interactor;
    }

    public LoginTokenResponse retrieveLoginToken() throws IOException {
        String s;
        HttpGet get = new HttpGet(wikiApiUrl + "?action=query&format=json&meta=tokens&type=login");
        s = this.executeAndRead(get);
        logger.info("Attempted "+get.getURI().toASCIIString()+ " got " + s);

        return gson.fromJson(s, LoginTokenResponse.class);
    }
    public CsrfTokenResponse retrieveCsrfToken() throws IOException, URISyntaxException {
        String s;
        URIBuilder uriBuilder = new URIBuilder();
        URI anURI = uriBuilder
                .setScheme("https")
                .setHost("home.blazingumbra.com")
                .setPath("/wiki/api.php")
                .addParameter("action", "query")
                .addParameter("format", "json")
                .addParameter("meta", "tokens")
                .addParameter("type", "csrf")
                .build();
        HttpGet get = new HttpGet(anURI);
        s = this.executeAndRead(get);
        logger.info("Attempted "+get.getURI().toASCIIString()+ " got " + s);
        return gson.fromJson(s, CsrfTokenResponse.class);
    }

    private String executeAndRead(HttpUriRequest aRequest) throws IOException {
        String s;
        HttpResponse response = aClient.execute(aRequest);
        ReaderMaker maker = new ReaderMaker();
        BufferedReader br = maker.fromInputStream(response.getEntity().getContent());
        s = br.readLine();
        br.close();
        logger.info("Read " + s + "from " + aRequest.toString());
        return s;
    }
    public String actionsearch(String query) throws IOException {
        WikiReader wikiReader = new WikiReader();
        String s;
        String mainUrlBuilder = wikiApiUrl +
                "?action=opensearch&format=json&redirects=resolve&search=" +
                URLEncoder.encode(query, "UTF-8");
        s = wikiReader.readFrom(new URL(mainUrlBuilder));
        logger.info("Got " +s + "from " + mainUrlBuilder);
        return s;
    }

    public void login(String username, String password, String loginToken) throws IOException, URISyntaxException, LoginFailedException {
        String s;
        URIBuilder uriBuilder = new URIBuilder();
        URI anURI = uriBuilder
                .clearParameters()
                .removeQuery()
                .setScheme("https")
                .setHost("home.blazingumbra.com")
                .setPath("/wiki/api.php")
                .addParameter("action", "login")
                .build();
        loginToken = URLEncoder.encode(loginToken, "UTF-8");
        HttpPost post = new HttpPost(anURI);
        logger.info("Post made to "+anURI.toASCIIString() + ".");

        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity entity = new StringEntity("&format=json&lgname="+username+"&lgpassword="+password+"&lgtoken="+loginToken);
        post.setEntity(entity);
        s = this.executeAndRead(post);
        logger.info("Posted to " + entity.getContent() + ", " + "got " + s);

        LoginResponse loginResponse = gson.fromJson(s, LoginResponse.class);
        if(!loginResponse.hasSucceeded())
            throw new LoginFailedException(s);
    }
    public EditResponse createPage(String title, String content, String csrfToken) throws IOException, URISyntaxException {
        String encodedTitle = URLEncoder.encode(title, "UTF-8");
        String encodedContent = URLEncoder.encode(content, "UTF-8");
        String encodedToken = URLEncoder.encode(csrfToken, "UTF-8");
        String pageContentAfterExecution;

        String entityLiteral = "action=edit&format=json&title="+encodedTitle+"&text="+encodedContent+"&bot=1&token="+encodedToken;
        HttpEntity entity = new StringEntity(entityLiteral);
        URIBuilder uriBuilder = new URIBuilder();
        URI anURI = uriBuilder
                .clearParameters()
                .removeQuery()
                .setScheme("https")
                .setHost("home.blazingumbra.com")
                .setPath("/wiki/api.php")
                .build();
        HttpPost post = new HttpPost(anURI);
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        post.setEntity(entity);
        pageContentAfterExecution = this.executeAndRead(post);
        return gson.fromJson(pageContentAfterExecution, EditResponse.class);
    }
    public EditResponse editPageByID(String pageID, String content, String csrfToken) throws IOException, URISyntaxException {
        String encodedID = URLEncoder.encode(pageID, "UTF-8");
        String encodedContent = URLEncoder.encode(content, "UTF-8");
        String encodedToken = URLEncoder.encode(csrfToken, "UTF-8");
        String pageContentAfterExecution;

        String entityLiteral = "action=edit&format=json&pageid="+encodedID+"&text="+encodedContent+"&bot=1&token="+encodedToken;
        HttpEntity entity = new StringEntity(entityLiteral);
        URIBuilder uriBuilder = new URIBuilder();
        URI anURI = uriBuilder
                .clearParameters()
                .removeQuery()
                .setScheme("https")
                .setHost("home.blazingumbra.com")
                .setPath("/wiki/api.php")
                .build();
        HttpPost post = new HttpPost(anURI);
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        post.setEntity(entity);
        pageContentAfterExecution = this.executeAndRead(post);
        return gson.fromJson(pageContentAfterExecution, EditResponse.class);

    }

    public String getPageText(String pageID) throws IOException, URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder();
        URI anURI = uriBuilder
                .clearParameters()
                .removeQuery()
                .setScheme("https")
                .setHost("home.blazingumbra.com")
                .setPath("/wiki/api.php")
                .addParameter("action", "parse")
                .addParameter("format", "json")
                .addParameter("pageid", pageID)
                .addParameter("prop", "wikitext")
                .build();
        HttpGet get = new HttpGet(anURI);
        String s = this.executeAndRead(get);
        ParseResponse parseResponse = gson.fromJson(s, ParseResponse.class);
        String pageText = parseResponse.getParseObject().getWikitext().getText();
        return pageText;
    }

    @Override
    public void close() {}
}
