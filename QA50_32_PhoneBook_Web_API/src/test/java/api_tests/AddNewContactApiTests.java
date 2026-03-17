package api_tests;

import dto.*;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.BaseApi;

import java.io.IOException;

import static utils.UserFactory.positiveUser;
import static utils.UserFactory.positiveUserLogin;
import static utils.PropertiesReader.*;
import static utils.ContactFactory.*;

public class AddNewContactApiTests implements BaseApi {

    TokenDto token;
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void login() {
//        User user = positiveUserLogin();
        User user = new User(getProperty("base.properties", "login"), getProperty("base.properties", "password"));
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
        if (response.code() == 200) {
            try {
                token = GSON.fromJson(response.body().string(), TokenDto.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void addNewContactPositiveApiTest() {
        Contact contact = positiveContact();
        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT_URL)
                .addHeader(AUTH, token.getToken())
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 200);
    }

    @Test
    public void addNewContactPositiveApiTest2() {
        Contact contact = positiveContact();
        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT_URL)
                .addHeader(AUTH, token.getToken())
                .post(requestBody)
                .build();
        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            softAssert.assertEquals(response.code(), 200, "validate status code");
            ResponseMessageDto responseMessageDto = GSON.fromJson(response.body().string(), ResponseMessageDto.class);
            softAssert.assertTrue(responseMessageDto.getMessage().contains("Contact was added!"), "validate message");
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("exception is created");
        }
    }

    @Test
    public void addNewContactNegative_WOToken_ApiTest() {
        Contact contact = positiveContact();
        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 403);
    }

    @Test
    public void addNewContactNegative_WrongToken_ApiTest() {
        Contact contact = positiveContact();
        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT_URL)
                .addHeader(AUTH, "token.getToken()")
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 401);
    }

    @Test
    public void addNewContactNegative_WrongMediaType_ApiTest() {
        Contact contact = positiveContact();
        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), TEXT);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT_URL)
                .addHeader(AUTH, token.getToken())
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 500);
    }

    //==========================================HW17===================================/
    @Test
    public void addNewContact_ContactAlreadyExists_NegativeApiTest_BUG() {
        Contact contact = contactAlreadyExists();
        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT_URL)
                .addHeader(AUTH, token.getToken())
                .post(requestBody)
                .build();
        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            ErrorMessageDto errorMessageDto = GSON.fromJson(response.body().string(), ErrorMessageDto.class);
            softAssert.assertEquals(response.code(), 409, "validate status code");
            softAssert.assertEquals(errorMessageDto.getError().contains("Conflict"), "validate error name");
            softAssert.assertTrue(errorMessageDto.getMessage().contains("Contact already exists!"), "validate message");
            softAssert.assertAll();
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("exception is created");
        }
    }

    @Test
    public void addNewContactNegative_WrongFormatText_ApiTest() {
        Contact contact = positiveContact();
        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), TEXT);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT_URL)
                .addHeader(AUTH, token.getToken())
                .post(requestBody)
                .build();
        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            ErrorMessageDto errorMessageDto = GSON.fromJson(response.body().string(), ErrorMessageDto.class);
            softAssert.assertEquals(response.code(), 500, "validate status code");
            softAssert.assertTrue(errorMessageDto.getMessage().contains("not supported"), "validate message");
            softAssert.assertTrue(errorMessageDto.getError().contains("Internal Server Error"), "validate error name");
            softAssert.assertAll();
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("exception is created");
        }
    }

    @Test
    public void addNewContactNegative_WrongEndpoint_ApiTest() {
        Contact contact = positiveContact();
        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .addHeader(AUTH, token.getToken())
                .post(requestBody)
                .build();
        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            ErrorMessageDto errorMessageDto = GSON.fromJson(response.body().string(), ErrorMessageDto.class);
            softAssert.assertEquals(response.code(), 400, "validate status code");
            softAssert.assertTrue(errorMessageDto.getMessage().contains("Wrong format Credential Object"), "validate error message");
            softAssert.assertTrue(errorMessageDto.getError().contains("Bad Request"), "validate error name");
            softAssert.assertAll();
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("exception is created");
        }
    }

    @Test
    public void addNewContactNegative_WrongKey_ApiTest() {
        Contact contact = positiveContact();
        contact.setName("");
        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT_URL)
                .addHeader(AUTH, token.getToken())
                .post(requestBody)
                .build();
        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            ErrorMessageDto errorMessageDto = GSON.fromJson(response.body().string(), ErrorMessageDto.class);
            System.out.println(errorMessageDto.toString());
            softAssert.assertEquals(response.code(), 400, "validate status code");
            softAssert.assertTrue(errorMessageDto.getMessage().contains("Wrong format Credential Object"), "validate error message");
            softAssert.assertTrue(errorMessageDto.getError().contains("Bad Request"), "validate error name");
            softAssert.assertAll();
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("exception is created");
        }
    }




}
