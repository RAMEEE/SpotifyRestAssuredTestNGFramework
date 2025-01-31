package com.spotify.oauth2.api.applicationAPI;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojos.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.TokenManager.getToken;

public class PlaylistAPI {

    @Step
    public static Response post(Playlist requestPlaylist){
        return RestResource.post(getToken(), USERS+"/"+ ConfigLoader.getInstance().getUser_id()+PLAYLISTS, requestPlaylist);
    }

    @Step
    public static Response post(String token, Playlist requestPlaylist){
        return RestResource.post(token, USERS+"/"+ ConfigLoader.getInstance().getUser_id()+PLAYLISTS, requestPlaylist);
    }

    @Step
    public static Response get(String playlistID){
        return RestResource.get(getToken(), PLAYLISTS+"/" + playlistID);
    }

    @Step
    public static Response update(Playlist requestPlaylist, String playlistID){
        return RestResource.update(getToken(), PLAYLISTS+"/"+ playlistID, requestPlaylist);
    }
}
