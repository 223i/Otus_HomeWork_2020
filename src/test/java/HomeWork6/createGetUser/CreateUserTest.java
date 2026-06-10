package HomeWork6.createGetUser;

import HomeWork6.dto.User;
import HomeWork6.services.UserService;
import io.restassured.response.Response;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.hamcrest.Matchers.*;

public class CreateUserTest {
    protected UserService userService = new UserService();

    /**
     * Тест проверяет возможность создания юзера.
     *0. Создать объект юзера и передать в него значения полей
     *1. Отправить POST запрос на сервер с передачей юзера.
     *2. Получить ответ от сервера и проверить следующие данные:
     * 2.1. Статус код 200
     * 2.2. Поля type, message совпадают с ожидаемыми значениями полей
     */
    @Test
    public void checkCreateValidUser() {

        Response response;
        User user;
        String expectedEmail = "ValidUserTest@gmail.com";
        String expectedType = "unknown";
        Long id = 101L;

        user = User.builder()
                .email(expectedEmail)
                .firstName("Irina")
                .id(id)
                .lastName("Korobeynikova")
                .password("Password")
                .phone("8-800-555-55-55")
                .username("ValidUser")
                .userStatus(10L)
                .build();

        response = userService.addUserRequest(user);

        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .time(lessThan(5000L))
                .body("type", equalTo(expectedType))
                .body("message", comparesEqualTo(id.toString()));
    }

    /**
     * Тест проверяет поведение системы при создании юзера с невалидными значениями полей.
     *0. Создать объект юзера и передать в него значения полей: В поле id поместить негативное значение
     *1. Отправить POST запрос на сервер с передачей юзера.
     *2. Получить ответ от сервера и проверить следующие данные:
     * 2.1. Статус код 500
     * 2.2. Поле message совпадает с ожидаемым значением
     */
    @Test
    public void checkCreateInvalidUser() {

        Response response;
        User user;
        Long id = -1010000948484L;

        user = User.builder()
                .id(id)
                .username("InvalidUser")
                .userStatus(id)
                .build();
        response = userService.addUserRequest(user);
        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .body("message", comparesEqualTo("something bad happened"));
    }

    /**
     * Тест проверяет возможность создания юзера.
     *0. Создать объекты юзеров и передать в них рандомные значения полей. Поместить юзеров в лист
     *1. Отправить POST запрос на сервер с передачей юзера.
     *2. Получить ответ от сервера и проверить следующие данные:
     * 2.1. Статус код 200
     */
    @Test
    public void checkCreateSeveralUsers() {

        Random random = new Random();
        Response response;
        User[] users = new User[5];
        String[] firstNamesDictionary = new String[] { "Jack", "John", "Samuel", "Tomas", "Daniel", "Harry","Michael"};
        String[] lastName = new String[] { "Luis", "Carrol", "Dwait", "Qwerty", "Smith", "Poul" };
        String[] phoneNumbers = new String[] { "88003339090", "8-800-333-90-0", "1111111", "232323", "2-00-00", "4-55-556-7-99" };
        Long[] id = new Long[] { 88L, 80L, 1111111L, 232323L, 2L, 4L };

        for (int i = 0; i < 5; i++) {
            User user = User.builder()
                    .email(firstNamesDictionary[random.nextInt(firstNamesDictionary.length)] + "@gmail.com")
                    .firstName(firstNamesDictionary[random.nextInt(firstNamesDictionary.length)])
                    .id(id[random.nextInt(id.length)])
                    .lastName(lastName[random.nextInt(lastName.length)])
                    .password(lastName[random.nextInt(lastName.length)])
                    .phone(phoneNumbers[random.nextInt(phoneNumbers.length)])
                    .username("ValidUser")
                    .userStatus(id[random.nextInt(id.length)])
                    .build();
            users[i] = user;
        }


        response = userService.addUsersWithListRequest(users);

        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }
}
