package new_LookupBot.wiki;

import new_LookupBot.util.ReaderMaker;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;

public class WikiReader {
    public String readFrom(URL aUrl) throws IOException {
        ReaderMaker readerMaker = new ReaderMaker();
        BufferedReader br = readerMaker.fromInputStream(aUrl.openStream());
        String read = br.readLine();
        br.close();
        return read;
    }
}
