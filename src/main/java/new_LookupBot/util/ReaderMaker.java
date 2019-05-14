package new_LookupBot.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReaderMaker {
    public BufferedReader fromInputStream(InputStream inputStream)
    {
        return new BufferedReader(new InputStreamReader(inputStream));
    }
}
