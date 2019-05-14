package new_LookupBot.objects;

public class LoginTokenResponse {
    String batchcomplete;
    private TokenQuery query;

    public String getToken() {
        return query.getTokens().getLogintoken();
    }

    static class TokenQuery {
        private TokenResult tokens;

        TokenResult getTokens() {
            return tokens;
        }

        static class TokenResult {
            private String logintoken;

            String getLogintoken() {
                return logintoken;
            }
        }
    }
}
