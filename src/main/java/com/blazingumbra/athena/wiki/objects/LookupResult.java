package com.blazingumbra.athena.wiki.objects;

import java.util.Arrays;

@SuppressWarnings("ALL")
public class LookupResult {
    private String key;
    private String[] links1;
    private String[] empties;
    private String[] link2;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "LookupResult{" +
                "key='" + key + '\'' +
                ", links1=" + Arrays.toString(links1) +
                ", empties=" + Arrays.toString(empties) +
                ", link2=" + Arrays.toString(link2) +
                '}';
    }
}
