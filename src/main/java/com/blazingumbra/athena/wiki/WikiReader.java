package com.blazingumbra.athena.wiki;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

class WikiReader {
    String readFrom(URL aUrl) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(aUrl.openStream()));
        String read = br.readLine();
        br.close();
        return read;
    }
}
