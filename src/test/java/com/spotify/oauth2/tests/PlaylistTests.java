package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationAPI.PlaylistAPI;
import com.spotify.oauth2.pojos.Error;
import com.spotify.oauth2.pojos.Playlist;

import com.spotify.oauth2.utils.DataLoader;
import io.qameta.allure.*;
import io.restassured.response.Response;

import org.testng.annotations.Test;


import static com.spotify.oauth2.utils.FakerUtils.generateDescription;
import static com.spotify.oauth2.utils.FakerUtils.generateName;
import static io.qameta.allure.SeverityLevel.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify OAuth 2.0")
@Feature("Playlist API")
public class PlaylistTests extends BaseTest {

    @Story("Create a playlist story")
    @Severity(CRITICAL)
    @Owner("R@m")
    @Link(name = "Website", url = "https://api.spotify.com")
    @Issue("Defect_123")
    @TmsLink("TMS-456")
    @Description("Create a playlist for a Spotify user. (The playlist will be empty until you add tracks.)")
    @Test(description = "should be able to create a playlist")
    public void shouldBeAbleToCreatePlaylist(){
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistAPI.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_201);
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
    }

    @Test
    public void shouldBeAbleToGetPlaylist(){
        Playlist requestPlaylist = playlistBuilder("Playlist SxvJJ 91Da", "Description 1LEUf8###8.+++ud_C9  _ k_ E#4-I@_+5y+g+_+_+# ___K_", true);
        Response response = PlaylistAPI.get(DataLoader.getInstance().getPlaylistID());
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
    }

    @Test
    public void shouldBeAbleToUpdatePlaylist(){
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistAPI.update(requestPlaylist, DataLoader.getInstance().getUpdatePlaylistID());
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
    }

    @Story("Create a playlist story")
    @Test
    public void shouldNotBeAbleToCreatePlaylistWithOutName(){
        Playlist requestPlaylist = playlistBuilder("", generateDescription(), false);
        Response response = PlaylistAPI.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_400);
        assertErrorMessage(response.as(Error.class), StatusCode.CODE_400);
    }

    @Story("Create a playlist story")
    @Test
    public void shouldNotBeAbleToCreatePlaylistWithExpiredToken(){
        String invalidToken = "1234";
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistAPI.post(invalidToken, requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_401);
        assertErrorMessage(response.as(Error.class), StatusCode.CODE_401);
    }

    @Step
    public Playlist playlistBuilder(String name, String description, boolean _public){
        return Playlist.builder().
                name(name).
                description(description).
                _public(_public).
                build();
    }

    @Step
    public void assertPlaylistEqual(Playlist responsePlaylist, Playlist requestPlaylist){
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));
    }

    @Step
    public void assertStatusCode(int actualStatusCode, StatusCode statusCode){
        assertThat(actualStatusCode, equalTo(statusCode.code));
    }

    @Step
    public void assertErrorMessage(Error responseErr, StatusCode statusCode){
        assertThat(responseErr.getError().getStatus(), equalTo(statusCode.code));
        assertThat(responseErr.getError().getMessage(), equalTo(statusCode.msg));
    }

}
