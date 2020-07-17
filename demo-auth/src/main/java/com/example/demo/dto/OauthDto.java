package com.example.demo.dto;

public class OauthDto {

    private String access_token;
    private String scope;
    private String bearer;

    public String getAccess_token() {
        return access_token;
    }

    @Override
    public String toString() {
        return "OauthDto{" +
                "access_token='" + access_token + '\'' +
                ", scope='" + scope + '\'' +
                ", bearer='" + bearer + '\'' +
                '}';
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }
}
