package api.client;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.Token;
import model.User;

import java.io.File;

import static defaults.Defaults.*;
import static io.restassured.RestAssured.given;

public class UserClient {

    @Step("Get response for correct user create")
    public Response getCreateUserResponse(User user) {
        RestAssured.baseURI = BASE_URI;
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(BASE_URI + USER_AUTH_URI_SUBPATH + "/register");
    }

    @Step("Get response for correct user log in")
    public Response getLogInUserResponse(User user) {
        RestAssured.baseURI = BASE_URI;
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(BASE_URI + USER_AUTH_URI_SUBPATH + "/login");
    }

    @Step("Get response for correct user deletion")
    public Response getDeleteUserResponse(String token) {
        RestAssured.baseURI = BASE_URI;
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .when()
                .delete(BASE_URI + USER_AUTH_URI_SUBPATH + "/user");
    }

    @Step("Parse access token from response")
    public String parseAccessTokenFromResponse(Response response) {
        Token token = response.body().as(Token.class);
        return token.getAccessToken();
    }


    public String getAccessTokenForUser(User user) {
        return parseAccessTokenFromResponse(getLogInUserResponse(user));
    }
}
