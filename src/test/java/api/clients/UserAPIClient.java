package api.clients;

import api.models.UserRequestDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static utils.PropertyUtils.getPropertyFromConfigFile;

public class UserAPIClient {

    private static final String BASE_URL = getPropertyFromConfigFile("base_uri");
    private static final String USER = "/user";

    public static Response addNewUser(UserRequestDTO userRequestDTO) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL + USER)
                .body(userRequestDTO)
                .post();
    }

    public static Response modifyUser(UserRequestDTO userRequestDTO) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL + USER)
                .body(userRequestDTO)
                .put();
    }

    public static Response getUser(String username) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL + USER + "/" + username)
                .get();
    }

    public static Response deleteUser(String id) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL + USER + "/" + id)
                .delete();
    }
}
