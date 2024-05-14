package api.clients;

import api.models.UserRequestDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static utils.PropertyUtils.getPropertyFromConfigFile;

public class UserAPIClient {

    private static final String BASE_USER_URI = "/user";

    public static Response addNewUser(UserRequestDTO userRequestDTO) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(getPropertyFromConfigFile("base_uri") + BASE_USER_URI)
                .body(userRequestDTO)
                .post();
    }

    public static Response getAllUsers( ) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(getPropertyFromConfigFile("base_uri") + BASE_USER_URI)
                .get();

    }

    public static Response modifyUser(UserRequestDTO userRequestDTO) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(getPropertyFromConfigFile("base_uri") + BASE_USER_URI)
                .body(userRequestDTO)
                .put();
    }

    public static Response getUser(String username) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(getPropertyFromConfigFile("base_uri") + BASE_USER_URI+"/"+username) // Updated path to include specific user
                .get();
    }

    public static Response deleteUser(String id) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(getPropertyFromConfigFile("base_uri") + BASE_USER_URI+"/"+ id) // Updated path to include specific user
                .delete();
    }
}
