package com.blazingumbra.athena.wiki.objects;

public class EditResponse {
    private Edit edit;

    private String getResult() {

        return edit.getResult();
    }
    public String getPageid() {

        return edit.getPageid();
    }
    public String getTitle(){
        return edit.getTitle();
    }
    public String getRevID() {
        return edit.getNewrediv();
    }

    class Edit {
        String _new;
        String result;
        String pageid;

        String getResult() {
            return result;
        }

        String getTitle() {
            return title;
        }

        String getNewrediv() {
            return newrediv;
        }
        String getPageid() {
            return pageid;
        }

        String title;
        String contentmodel;
        String oldrevid;

        String newrediv;
        String newtimestamp;
    }

    public boolean hasSucceeded() {
        return getResult().equals("Success");
    }
}
