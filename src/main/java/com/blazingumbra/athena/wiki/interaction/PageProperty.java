package com.blazingumbra.athena.wiki.interaction;

public class PageProperty {
    private String pageKey;
    private String content;
    private String csrfToken;

    public PageProperty(String pageKey, String content, String csrfToken) {
        this.pageKey = pageKey;
        this.content = content;
        this.csrfToken = csrfToken;
    }

    String getPageKey() {
        return pageKey;
    }

    String getContent() {
        return content;
    }

    String getCsrfToken() {
        return csrfToken;
    }
}
