package com.spotify.oauth2.api;

import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static com.spotify.oauth2.api.RestResource.postAccount;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class TokenManager {
    private static String access_token;
    private static Instant expiry_time;

    public synchronized static String getToken(){
        try {
            if(access_token==null || Instant.now().isAfter(expiry_time)){
                System.out.println("Renewing token ...");
                Response response = renewToken();
                access_token = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
            }else{
                System.out.println("Token is good to use");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("ABORT!! Failed to get token");
        }
        return access_token;
    }

    private static Response renewToken(){
        HashMap<String, String> formParams = new HashMap<String, String>();
        formParams.put("grant_type", ConfigLoader.getInstance().getGrant_type());
        formParams.put("refresh_token", ConfigLoader.getInstance().getRefresh_token());
        formParams.put("client_id", ConfigLoader.getInstance().getClient_id());
        formParams.put("client_secret", ConfigLoader.getInstance().getClient_secret());
        Response response = postAccount(formParams);
        if(response.statusCode() !=200){
            throw new RuntimeException("ABORT!! Renew Token Failed");
        }
        return response;
    }
}
