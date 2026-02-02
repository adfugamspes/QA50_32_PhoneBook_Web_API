package ui_tests;

import dto.User;
import manager.AppManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.UserFactory;

import static utils.UserFactory.*;
import java.time.Duration;

public class LoginTests extends AppManager {

    LoginPage loginPage;
    UserFactory faker;

    @BeforeMethod
    public void goToRegistrationPage() {
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void loginPositiveTest () {
        loginPage.typeLoginRegistrationForm("testmail123@mail.com", "Password!123");
        loginPage.clickBtnLoginForm();
        Assert.assertTrue(new ContactsPage(getDriver()).isTextInBtnAddPresent("ADD"));
    }

    @Test
    public void loginPositiveTest_WithUser () {
        User user = positiveUserLogin();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertTrue(new ContactsPage(getDriver()).isTextInBtnSignOutPresent("Sign Out"));
    }

    @Test
    public void loginNegativeTest_WrongEmail(){
        User user = new User("testmail123ail.com", "Password!123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_BlankEmail(){
        User user = positiveUserLogin();
        user.setUsername("");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_OnlySpacesEmail(){
        User user = positiveUserLogin();
        user.setUsername("   ");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_2AtSignsInEmail(){
        User user = positiveUserLogin();
        user.setUsername("testmail123@@mail.com");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_NoLettersBeforeAtSignEmail(){
        User user = positiveUserLogin();
        user.setUsername("@mail.com");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_NoLettersAfterAtSignEmail(){
        User user = positiveUserLogin();
        user.setUsername("testmail123@");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_CyrillicLettersEmail_BUG(){
        User user = positiveUserLogin();
        user.setUsername("еуыеуьфшууукд@gmail.com");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_blankPassword(){
        User user = positiveUserLogin();
        user.setPassword("");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("Wrong email or password"));
    }

    @Test
    public void loginNegative_NoSpecialCharsPassword(){
        User user = positiveUserLogin();
        user.setPassword("Password123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_WrongSpecialCharPassword(){
        User user = positiveUserLogin();
        user.setPassword("Password)123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_NoLatinLettersPassword(){
        User user = positiveUserLogin();
        user.setPassword("Зфыыцщкв!123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_LatinWithSerifPassword(){
        User user = positiveUserLogin();
        user.setPassword("Pássword!123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_NoUppercasePassword(){
        User user = positiveUserLogin();
        user.setPassword("password!123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_NoLowercasePassword(){
        User user = positiveUserLogin();
        user.setPassword("PASSWORD!123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_NoNumbersPassword(){
        User user = positiveUserLogin();
        user.setPassword("Password!");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

        @Test
    public void loginNegative_UnregisteredUserPassword(){
        User user = positiveUser();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }
}
