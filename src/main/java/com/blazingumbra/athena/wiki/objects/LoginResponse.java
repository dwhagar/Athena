package com.blazingumbra.athena.wiki.objects;

public class LoginResponse {
    private Login login;

    private String getResult()
    {
        return login.result;
    }

    static class Login {
        String result;
        String lguserid;
        String lgusername;
    }

    public boolean hasSucceeded()
    {
        return getResult().equalsIgnoreCase("Success");
    }
}
