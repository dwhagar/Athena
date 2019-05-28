package com.blazingumbra.athena.wiki.interaction;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class SearchWikiInteraction {
    public static String execute(WikiSession aWikiSession, String keyWord) throws IOException, URISyntaxException {
        String s;
        String encodedKeyword;
        URI anURI;
        URIBuilder uriBuilder = new URIBuilder(aWikiSession.getBaseURI());

        encodedKeyword = URLEncoder.encode(keyWord, "UTF-8");
        anURI = uriBuilder
                .addParameter("action", "opensearch")
                .addParameter("format", "json")
                .addParameter("redirects", "resolve")
                .addParameter("search", encodedKeyword)
                .build();
        HttpGet get = new HttpGet(anURI);
        s = aWikiSession.getResult(get);
        return s;
    }
}
