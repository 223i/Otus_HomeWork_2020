package HomeWork6.createGetUser;

import HomeWork6.services.UserService;
import io.restassured.response.Response;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class GetUserByNameTest {
    protected UserService userService = new UserService();

    /**
     * Тест проверяет возможность получения юзера по имени.
     * Тестовые данные: Юзер с именем "user1", который предзаложен на сервере
     *
     *1. Отправить Get запрос на сервер с передачей нужного имени пользователя.
     *2. Получить ответ от сервера и проверить следующие данные:
     * 2.1. Статус код 200
     * 2.2. Поля id, username, userStatus полученного юзера совпадают с ожидаемыми значениями полей
     */
    @Test
    public void checkGetValidUserByName(){
        Response response;
        String expectedUserName = "user1";

        response = userService.getUserByNameRequest(expectedUserName);
        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .time(lessThan(5000L))
                .body("id", equalTo(1))
                .body("username", comparesEqualTo(expectedUserName))
                .body("userStatus", comparesEqualTo(0));
    }

    /**
     * Негативный сценарий. Тест проверяет ответ сервера при запросе несуществующео юзера.
     *
     *1. Отправить Get запрос на сервер с передачей несуществующего имени пользователя.
     *2. Получить ответ от сервера и проверить следующие данные:
     * 2.1. Статус код 404
     * 2.2. Значения полей type, message совпадают с ожидаемыми значениями
     */
    @Test
    public void checkGetUserByName_UserNotFound(){
        Response response;
        String expectedUserName = "q$3";

        response = userService.getUserByNameRequest(expectedUserName);
        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .time(lessThan(5000L))
                .body("type", comparesEqualTo( "error"))
                .body("message", comparesEqualTo( "User not found"));
    }
}
