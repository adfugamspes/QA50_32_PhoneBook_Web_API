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
    public void loginPositiveTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationForm("testmail123@mail.com", "Password!123");
        loginPage.clickBtnLoginForm();
//        ContactsPage contactsPage = new ContactsPage(getDriver());
//        Assert.assertTrue(contactsPage.isBtnSignOutDisplayed());
        Assert.assertTrue(new ContactsPage(getDriver()).isTextInBtnAddPresent("ADD"));
    }

    @Test
    public void loginPositiveTestWithUser() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(new User("testmail123@mail.com", "Password!123"));
        loginPage.clickBtnLoginForm();
//        ContactsPage contactsPage = new ContactsPage(getDriver());
//        Assert.assertTrue(contactsPage.isBtnAddContactDisplayed());
        Assert.assertTrue(new ContactsPage(getDriver()).isTextInBtnSignOutPresent("Sign Out"));
    }

    @Test
    public void loginNegativeTestWrongEmail() {
        User user = new User("testmail123ail.com", "Password!123");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_BlankEmail() {
        User user = negativeBlankEmail();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_OnlySpacesEmail() {
        User user = negativeOnlySpacesEmail();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_2AtSignsInEmail() {
        User user = new User("testemail2@@gmail.com", "Password!123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_NoLettersBeforeAtSignEmail() {
        User user = new User("@gmail.com", "Password!123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_NoLettersAfterAtSignEmail() {
        User user = new User("testemail2@", "Password!123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_OnlyCyrillicLettersEmail_BUG() {
        User user = new User("еуыеуьфшууукд@gmail.com", "Password!123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }
    //CW6 - changed

    @Test
    public void loginNegative_blankPasswordCW() {
        User user = positiveUser();
        user.setPassword("");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("Wrong email or password"));
        }

    @Test
    public void loginNegative_blankPassword() {
        User user = new User("testemail2@gmail.com", "");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_NoSpecialCharsPassword() {
        User user = new User("testemail2@gmail.com", "Password123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_WrongSpecialCharPassword() {
        User user = new User("testemail2@gmail.com", "Password)123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_NoLatinLettersPassword() {
        User user = new User("testemail2@gmail.com", "Зфыыцщкв!123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_LatinWithSerifPassword() {
        User user = new User("testemail2@gmail.com", "Pássword!123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_NoUppercasePassword() {
        User user = new User("testemail2@gmail.com", "password!123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_NoLowercasePassword() {
        User user = new User("testemail2@gmail.com", "PASSWORD!123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_NoNumbersPassword() {
        User user = new User("testemail2@gmail.com", "Password!");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_7SymbolsPassword() {
        User user = new User("testemail2@gmail.com", "!123Pas");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_16SymbolsPassword() {
        User user = new User("testemail2@gmail.com", "!123PasswordQwer");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegative_UnregisteredUserPassword() {
        User user = positiveUser();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

}
