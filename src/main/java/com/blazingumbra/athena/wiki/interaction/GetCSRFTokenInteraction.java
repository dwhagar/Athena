package com.blazingumbra.athena.wiki.interaction;

import com.blazingumbra.athena.wiki.objects.CsrfTokenResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class GetCSRFTokenInteraction extends AbstractWikiInteraction{
    public static CsrfTokenResponse execute(WikiSession aWikiSession, String parameter) throws IOException, URISyntaxException {
        String s;
        URIBuilder uriBuilder = new URIBuilder(aWikiSession.getBaseURI());
        URI anURI = uriBuilder
                .addParameter("action", "query")
                .addParameter("format", "json")
                .addParameter("meta", "tokens")
                .addParameter("type", "csrf")
                .build();
        HttpGet get = new HttpGet(anURI);
        s = aWikiSession.getResult(get);
        return gson.fromJson(s, CsrfTokenResponse.class);
    }
}
