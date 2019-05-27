package com.blazingumbra.athena.wiki.interaction;

import com.blazingumbra.athena.wiki.exceptions.LoginFailedException;
import com.blazingumbra.athena.wiki.objects.LoginResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class LoginInteraction extends AbstractWikiInteraction{
    public static void execute(WikiSession aWikiSession, String parameter) throws LoginFailedException, URISyntaxException, IOException {
        String s;
        String[] parameterArray;
        String loginToken;
        String username;
        String password;

        parameterArray = parameter.split(",");
        loginToken = parameterArray[0];
        username = parameterArray[1];
        password = parameterArray[2];

        URIBuilder uriBuilder = new URIBuilder(aWikiSession.getBaseURI());
        URI anURI = uriBuilder
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
    }
}
