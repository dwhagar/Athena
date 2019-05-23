package com.blazingumbra.athena.wiki.objects;

public class CsrfTokenResponse {
    String batchcomplete;
    private Query query;

    public String getToken()
    {
        return query.getTokens().getCsrftoken();
    }

    class Query {
        Tokens getTokens() {
            return tokens;
        }

        Tokens tokens;

        class Tokens{
            String getCsrftoken() {
                return csrftoken;
            }

            String csrftoken;
        }
    }
}
