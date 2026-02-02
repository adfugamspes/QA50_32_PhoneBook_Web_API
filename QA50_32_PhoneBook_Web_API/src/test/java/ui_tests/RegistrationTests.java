package ui_tests;

import dto.User;
import manager.AppManager;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.UserFactory;

import java.util.Random;

import static utils.UserFactory.*;

public class RegistrationTests extends AppManager {

    LoginPage loginPage;

    @BeforeMethod
    public void goToRegistrationPage() {
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void registrationPositiveTest() {
        int i = new Random().nextInt(1000);
        User user = new User("positive" + i + "@gmail.com", "Password!123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(new ContactsPage(getDriver()).isTextInContactPageMessagePresent("No Contacts here!"));
    }

    @Test
    public void registrationPositiveTest_WithFaker() {
        User user = positiveUser();
        System.out.println(user);
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(new ContactsPage(getDriver()).isTextInContactPageMessagePresent("No Contacts here!"));
    }

    @Test
    public void registrationNegativeTest_BlankEmail() {
        User user = positiveUser();
        user.setUsername("");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_BlankEmailAndPassword() {
        User user = positiveUser();
        user.setUsername("");
        user.setPassword("");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_OnlySpacesEmail() {
        User user = positiveUser();
        user.setUsername("   ");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_2AtSignsInEmail() {
        User user = positiveUser();
        user.setUsername("testingmail123@@mail.com");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_2AtSignsLettersBetweenEmail() {
        User user = positiveUser();
        user.setUsername("testingmail123@test@mail.com");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_NoLettersBeforeAtSignEmail() {
        User user = positiveUser();
        user.setUsername("@mail.com");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_NoLettersAfterAtSignEmail() {
        User user = positiveUser();
        user.setUsername("testingmail123@");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_OnlyCyrillicLettersEmail_BUG() {
        User user = positiveUser();
        user.setUsername("абракадабрас@мейл.ру");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_LatinAndCyrillicLettersEmail_BUG() {
        User user = positiveUser();
        user.setUsername("абракадабра@gmail.com");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_BlankPassword() {
        User user = positiveUser();
        user.setPassword("");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("Password must contain"));
    }

    @Test
    public void registrationNegativeTest_OnlySpacesPassword() {
        User user = positiveUser();
        user.setPassword("   ");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("Password must contain"));
    }

    @Test
    public void registrationNegativeTest_NoSpecialCharsPassword() {
        User user = positiveUser();
        user.setPassword("Password123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("Password must contain"));
    }

    @Test
    public void registrationNegativeTest_WrongSpecialCharPassword() {
        User user = positiveUser();
        user.setPassword("Password)123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("Password must contain"));
    }

    @Test
    public void registrationNegativeTest_CyrillicLettersPassword() {
        User user = positiveUser();
        user.setPassword("Пароль!123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("Password must contain"));
    }

    @Test
    public void registrationNegativeTest_CyrillicAndLatinLettersPassword_BUG() {
        User user = positiveUser();
        user.setPassword("Парjkm!123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("Password must contain"));
    }

    @Test
    public void registrationNegativeTest_LatinWithSerifPassword_BUG() {
        User user = positiveUser();
        user.setPassword("Password!123ł");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("Password must contain"));
    }

    @Test
    public void registrationNegativeTest_NoUppercasePassword() {
        User user = positiveUser();
        user.setPassword("password!123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("Password must contain"));
    }

    @Test
    public void registrationNegativeTest_NoLowercasePassword() {
        User user = positiveUser();
        user.setPassword("PASSWORD!123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("Password must contain"));
    }

    @Test
    public void registrationNegativeTest_NoNumbersPassword() {
        User user = positiveUser();
        user.setPassword("Password!no");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("Password must contain"));
    }

    @Test
    public void registrationNegativeTest_7SymbolsPassword() {
        User user = positiveUser();
        user.setPassword("Pass!12");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("Password must contain"));
    }

    @Test
    public void registrationNegativeTest_16SymbolsPassword_BUG() {
        User user = positiveUser();
        user.setPassword("Password!123jdki");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("Password must contain"));
    }

    @Test
    public void registrationNegativeTest_UserAlreadyExists(){
        User user = positiveUser();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        ContactsPage contactsPage = new ContactsPage(getDriver());
        Assert.assertTrue(contactsPage.isTextInContactPageMessagePresent("No Contacts here!"));
        contactsPage.clickBtnSignOut();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "User already exist");
    }
}
