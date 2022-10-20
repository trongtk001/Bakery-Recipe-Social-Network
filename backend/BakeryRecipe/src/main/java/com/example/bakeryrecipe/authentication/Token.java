package com.example.bakeryrecipe.authentication;

import java.util.Date;

public class Token {
    private String token;
    private String refreshToken;
    private Date expireTime;
    private Date refreshTokenExpireTime;

    public Token() {
    }

    public Token(String token, String refreshToken, Date expireTime, Date refreshTokenExpireTime) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.expireTime = expireTime;
        this.refreshTokenExpireTime = refreshTokenExpireTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getRefreshTokenExpireTime() {
        return refreshTokenExpireTime;
    }

    public void setRefreshTokenExpireTime(Date refreshTokenExpireTime) {
        this.refreshTokenExpireTime = refreshTokenExpireTime;
    }
}
