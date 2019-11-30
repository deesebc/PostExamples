package com.home.example.wiremockhttps.pojo;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Token {

    @SerializedName("access_token")
    private String accessToken;

    private String refreshToken;

    private String scope;

    private String tokenType;

    private long expiresIn;

    private String instance_url;

}
