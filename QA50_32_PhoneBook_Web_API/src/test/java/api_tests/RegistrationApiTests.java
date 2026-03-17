package api_tests;

import dto.TokenDto;
import dto.User;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseApi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static utils.UserFactory.*;


public class RegistrationApiTests implements BaseApi {
    @Test
    public void registrationPositiveApiTest() {
        User user = positiveUser();
        System.out.println(user);
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION_URL)
                .post(requestBody)
                .build();

        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request)
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(response.code());
        Assert.assertEquals(response.code(), 200);
    }

    @Test
    public void registrationNegative_WrongPassword_ApiTest() {
        User user = positiveUser();
        user.setPassword("wrong password");
        System.out.println(user);
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request)
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(response.code());
        Assert.assertEquals(response.code(), 400);
    }

    @Test
    public void registrationNegative_WrongUsername_ApiTest() {
        User user = positiveUser();
        user.setUsername("wrong username");
        System.out.println(user);
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request)
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(response.code());
        Assert.assertEquals(response.code(), 400);
    }

    @Test
    public void registrationNegative_UserAlreadyExists_ApiTest() {
        User user = positiveUserLogin();
        System.out.println(user);
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request)
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(response.code());
        Assert.assertEquals(response.code(), 409);
    }

    @Test
    public void registrationNegative_WrongFormatText_ApiTest() {
        User user = positiveUser();
        user.setPassword("wrong password");
        System.out.println(user);
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), TEXT);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request)
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 500);
    }

    @Test
    public void registrationNegative_WrongKeyUser_ApiTest() {
        User user = positiveUser();
        Map<String, String> invalidJSON = new HashMap<>();
        invalidJSON.put("name", user.getUsername());
        invalidJSON.put("password", user.getPassword());
        RequestBody requestBody = RequestBody.create(GSON.toJson(invalidJSON), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request)
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 500);
    }

    @Test
    public void registrationNegative_WrongEndpointBaseUrl_ApiTest_BUG() {
        User user = positiveUser();
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL_HTTP + REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request)
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 500);
    }

    @Test
    public void registrationNegative_WrongEndpoint_ApiTest() {
        User user = positiveUser();
        System.out.println(user);
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request)
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 401);
    }

}
