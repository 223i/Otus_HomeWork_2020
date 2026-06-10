package HomeWork6.services;

import HomeWork6.dto.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserService {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String CREATE_USER = "/user";
    private static final String CREATE_USERS_WITH_LIST = "/user/createWithList";
    private static final String GET_USER_BY_NAME = "/user/{username}";

    RequestSpecification spec;

    public UserService() {
        spec = given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL);
    }


    public Response addUserRequest(User user) {
        return given()
                .spec(spec)
                .with()
                .body(user)
                .when()
                .log().all()
                .post(CREATE_USER);
    }

    public Response addUsersWithListRequest(User[] users) {
        return given()
                .spec(spec)
                .with()
                .body(users)
                .when()
                .log().all()
                .post(CREATE_USERS_WITH_LIST);
    }

    public Response getUserByNameRequest(String userName){
        return given()
                .spec(spec)
                .with()
                .pathParam("username", userName)
                .when()
                .log().all()
                .get(GET_USER_BY_NAME);
    }

}
