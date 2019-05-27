package com.blazingumbra.athena.wiki.interaction;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class SearchWikiInteraction {
    public static String execute(WikiSession aWikiSession, String parameter) throws IOException, URISyntaxException {
        String s;
        String query = parameter;
        String encodedQuery;
        URI anURI;
        URIBuilder uriBuilder = new URIBuilder(aWikiSession.getBaseURI());

        encodedQuery = URLEncoder.encode(query, "UTF-8");
        anURI = uriBuilder
                .addParameter("action", "opensearch")
                .addParameter("format", "json")
                .addParameter("redirects", "resolve")
                .addParameter("search", encodedQuery)
                .build();
        HttpGet get = new HttpGet(anURI);
        s = aWikiSession.getResult(get);
        return s;
    }
}
