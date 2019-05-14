package new_LookupBot.main;

public class InternalSubmission {
    private String wikiUrl;
    private String revID;
    private String playerID;

    public InternalSubmission(String wikiUrl, String playerID, String revID) {
        this.wikiUrl = wikiUrl;
        this.playerID = playerID;
        this.revID = revID;
    }

    public String getRevID() {
        return revID;
    }
    public String getWikiUrl() {
        return wikiUrl;
    }

    public String getPlayerID() {
        return playerID;
    }
}
