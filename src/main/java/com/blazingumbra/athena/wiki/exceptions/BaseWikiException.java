package com.blazingumbra.athena.wiki.exceptions;

public class BaseWikiException extends Exception{
    private String pageContent;

    public String getPageContent() {
        return pageContent;
    }

    BaseWikiException(String pageContent) {
        this.pageContent = pageContent;
    }
}
