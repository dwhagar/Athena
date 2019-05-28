package com.blazingumbra.athena.wiki.interaction;

import com.blazingumbra.athena.wiki.objects.ParseResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class GetPageTextWikiInteraction extends AbstractWikiInteraction{
    public GetPageTextWikiInteraction() {
        super();
    }

    public static String execute(WikiSession aWikiSession, String pageID) throws URISyntaxException, IOException {
        URIBuilder uriBuilder = new URIBuilder(aWikiSession.getBaseURI());
        URI anURI = uriBuilder
                .addParameter("action", "parse")
                .addParameter("format", "json")
                .addParameter("pageid", pageID)
                .addParameter("prop", "wikitext")
                .build();

        HttpGet get = new HttpGet(anURI);
        String s = aWikiSession.getResult(get);
        ParseResponse parseResponse = gson.fromJson(s, ParseResponse.class);
        return parseResponse.getParseObject().getWikitext().getText();
    }
}
