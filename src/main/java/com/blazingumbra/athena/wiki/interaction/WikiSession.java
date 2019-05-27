package com.blazingumbra.athena.wiki.interaction;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WikiSession {
    private HttpClient aClient;
    private Logger logger = LoggerFactory.getLogger(WikiSession.class);

    public WikiSession() {
        aClient = HttpClients.createDefault();
    }

    public String getResult(HttpRequestBase aRequest) throws IOException {
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
}
