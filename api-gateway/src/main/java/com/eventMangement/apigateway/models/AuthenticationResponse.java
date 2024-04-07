package com.eventMangement.apigateway.models;

import java.util.Collection;
public class AuthenticationResponse {
    private String id;
    private String accessToken;
    private String refreshToken;
    private long expiresAt;
    private Collection<String> authorityList;
    private  String redirectUrl;

    public AuthenticationResponse(String id, String accessToken, String refreshToken, long expiresAt, Collection<String> authorityList, String redirectUrl) {
        this.id = id;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresAt = expiresAt;
        this.authorityList = authorityList;
        this.redirectUrl = redirectUrl;
    }

    public AuthenticationResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Collection<String> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(Collection<String> authorityList) {
        this.authorityList = authorityList;
    }
    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
