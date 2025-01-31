package com.spotify.oauth2.api;

import com.spotify.oauth2.pojos.Playlist;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.Route.API;
import static com.spotify.oauth2.api.Route.TOKEN;
import static com.spotify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {


    public static Response post(String accessToken, String path, Object requestPlaylist){
        return given(getRequestSpec()).
                auth().oauth2(accessToken).
                body(requestPlaylist).
                when().post(path).
                then().spec(getResponseSpec()).
                extract().response();
    }
    public static Response postAccount(HashMap<String, String> formParams){
        return given(getAccountRequestSpec()).
                formParams(formParams).
                when().post(API+TOKEN).
                then().spec(getResponseSpec()).
                extract().response();
    }
    public static Response get(String accessToken, String path){
        return given(getRequestSpec()).
                auth().oauth2(accessToken).
                when().get(path).
                then().spec(getResponseSpec()).
                extract().response();
    }
    public static Response update(String accessToken, String path, Object requestPlaylist){
        return given(getRequestSpec()).
                auth().oauth2(accessToken).
                body(requestPlaylist).
                when().put(path).
                then().spec(getResponseSpec()).
                extract().response();
    }
}
