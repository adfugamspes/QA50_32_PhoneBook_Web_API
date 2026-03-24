package api_tests;

import dto.ContactsDto;
import dto.TokenDto;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.BaseApi;
import utils.ILogin;

import java.io.IOException;

public class DeleteContactApiTests implements BaseApi, ILogin {

    TokenDto token;
    SoftAssert softAssert = new SoftAssert();
    String contactId;

    @BeforeClass
    public void login() {
        token = login_get_token();
    }

    @BeforeMethod
    public void get_first_contact_id() {
        Request request = new Request.Builder()
                .url(BASE_URL + GET_ALL_CONTACTS_URL)
                .addHeader(AUTH, token.getToken())
                .get()
                .build();
        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            ContactsDto contactsDto = GSON.fromJson(response.body().string(), ContactsDto.class);
            contactId = contactsDto.getContacts().get(0).getId();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteContactByIdPositiveApiTest() {
        Request request = new Request.Builder()
                .url(BASE_URL + DELETE_CONTACT_URL + contactId)
                .addHeader(AUTH, token.getToken())
                .delete()
                .build();
        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            Assert.assertEquals(response.code(), 200);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
