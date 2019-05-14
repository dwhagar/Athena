package new_LookupBot.objects;

public class ParseResponse {
    public Parse getParseObject() {
        return ParseObject;
    }

    Parse ParseObject;

    public class Parse {
        private String title;
        private float pageid;
        Wikitext WikitextObject;

        // Getter Methods
        public String getTitle() {
            return title;
        }
        public float getPageid() {
            return pageid;
        }
        public Wikitext getWikitext() {
            return WikitextObject;
        }
    }
    public class Wikitext {
        private String text;

        // Getter Methods
        public String getText () {
            return text;
        }
    }
}
