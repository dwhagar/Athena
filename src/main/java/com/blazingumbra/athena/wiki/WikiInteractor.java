package com.blazingumbra.athena.wiki;

import com.blazingumbra.athena.wiki.exceptions.LoginFailedException;
import com.blazingumbra.athena.wiki.objects.*;
import com.google.gson.Gson;
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
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class WikiInteractor implements AutoCloseable{
    private static HttpClient aClient;
    private static Gson gson;
    private static Logger logger = LoggerFactory.getLogger(WikiInteractor.class);
    private URI baseURI;

    private WikiInteractor(String wikiBaseUrl) throws URISyntaxException {
        baseURI = new URI(wikiBaseUrl);
    }
    public static WikiInteractor getInstance(String wikiBaseUrl) throws LoginFailedException, IOException, URISyntaxException {
        WikiInteractor interactor = new WikiInteractor(wikiBaseUrl);
        aClient = HttpClients.createDefault();
        gson = new Gson();

        LoginTokenResponse loginTokenResponse = interactor.retrieveLoginToken();
        interactor.login("Neo Akazuli@Script's_Wiki_Interaction_Tool", "f7uglcs7a7rjghjpvmv8fjbeh4vi6dkb", loginTokenResponse.getToken());
        return interactor;
    }

    private LoginTokenResponse retrieveLoginToken() throws IOException, URISyntaxException {
        String s;
        URI anURI;
        URIBuilder uriBuilder = new URIBuilder(baseURI);
        anURI = uriBuilder
                .addParameter("action", "query")
                .addParameter("format", "json")
                .addParameter("meta", "tokens")
                .addParameter("type", "login")
                .build();
        HttpGet get = new HttpGet(anURI);
        s = this.executeAndRead(get);
        logger.info("Attempted "+get.getURI().toASCIIString()+ " got " + s);

        return gson.fromJson(s, LoginTokenResponse.class);
    }
    public CsrfTokenResponse retrieveCsrfToken() throws IOException, URISyntaxException {
        String s;
        URIBuilder uriBuilder = new URIBuilder(baseURI);
        URI anURI = uriBuilder
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

    private void login(String username, String password, String loginToken) throws IOException, URISyntaxException, LoginFailedException {
        String s;
        URIBuilder uriBuilder = new URIBuilder(baseURI);
        URI anURI = uriBuilder
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

    private String executeAndRead(HttpUriRequest aRequest) throws IOException {
        String s;

        HttpResponse response = aClient.execute(aRequest);
        HttpEntity responseEntity = response.getEntity();
        InputStreamReader inputStreamReader = new InputStreamReader(responseEntity.getContent());
        BufferedReader br = new BufferedReader(inputStreamReader);
        s = br.readLine();
        br.close();
        logger.info("Read " + s + "from " + aRequest.toString());
        return s;
    }

    @Override
    public void close() {}
}