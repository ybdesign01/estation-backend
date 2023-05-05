package com.app.estation.dto;

import java.util.Objects;

public class AuthDto {
    private String token;
    private String msg;
    private UserDto user;

    private String refreshToken;

    public AuthDto() {
    }

    public AuthDto(String token, String refreshToken, String msg, UserDto user) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.msg = msg;
        this.user = user;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthDto authDto)) return false;
        return Objects.equals(token, authDto.token) && Objects.equals(msg, authDto.msg) && Objects.equals(user, authDto.user) && Objects.equals(refreshToken, authDto.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, msg, user, refreshToken);
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
