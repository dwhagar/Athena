package com.blazingumbra.athena.wiki.interaction;

import com.blazingumbra.athena.wiki.objects.LoginTokenResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class GetLoginTokenInteraction extends AbstractWikiInteraction{

    public static LoginTokenResponse execute(WikiSession aWikiSession, String parameter) throws IOException, URISyntaxException {
        String s;
        URI anURI;
        URIBuilder uriBuilder = new URIBuilder(aWikiSession.getBaseURI());
        anURI = uriBuilder
                .addParameter("action", "query")
                .addParameter("format", "json")
                .addParameter("meta", "tokens")
                .addParameter("type", "login")
                .build();
        HttpGet get = new HttpGet(anURI);
        s = aWikiSession.getResult(get);

        return gson.fromJson(s, LoginTokenResponse.class);
    }
}
