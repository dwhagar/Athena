package com.blazingumbra.athena.wiki.interaction;

import com.blazingumbra.athena.wiki.exceptions.LoginFailedException;
import com.blazingumbra.athena.wiki.objects.LoginResponse;
import com.blazingumbra.athena.wiki.objects.LoginTokenResponse;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class WikiAuthenticator {
    public static WikiSession login (String username, String password) throws IOException, LoginFailedException, URISyntaxException {
        String s;
        URI anURI;
        URIBuilder uriBuilder;
        HttpGet get;

        WikiSession aWikiSession = new WikiSession("https://home.blazingumbra.com/wiki/api.php");
        Gson gson = new Gson();

        uriBuilder = new URIBuilder(aWikiSession.getBaseURI());
        anURI = uriBuilder
                .addParameter("action", "query")
                .addParameter("format", "json")
                .addParameter("meta", "tokens")
                .addParameter("type", "login")
                .build();
        get = new HttpGet(anURI);
        s = aWikiSession.getResult(get);

        LoginTokenResponse loginTokenResponse = gson.fromJson(s, LoginTokenResponse.class);
        String loginToken = loginTokenResponse.getToken();

        uriBuilder = new URIBuilder(aWikiSession.getBaseURI());
        anURI = uriBuilder
                .addParameter("action", "login")
                .build();
        loginToken = URLEncoder.encode(loginToken, "UTF-8");
        HttpPost post = new HttpPost(anURI);

        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity entity = new StringEntity("&format=json&lgname="+username+"&lgpassword="+password+"&lgtoken="+loginToken);
        post.setEntity(entity);
        s = aWikiSession.getResult(post);

        LoginResponse loginResponse = gson.fromJson(s, LoginResponse.class);
        if(!loginResponse.hasSucceeded())
            throw new LoginFailedException(s);
        return aWikiSession;
    }
}
