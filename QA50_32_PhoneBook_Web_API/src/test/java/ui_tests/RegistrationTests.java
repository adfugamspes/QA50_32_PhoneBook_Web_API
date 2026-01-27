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
    public void registrationPositiveTestWIthFaker() {
        User user = positiveUser();
        System.out.println(user);
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(new ContactsPage(getDriver()).isTextInContactPageMessagePresent("No Contacts here!"));
    }

    @Test
    public void registrationNegativeTest_BlankEmail() {
        User user = negativeBlankEmail();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.isAlertContainsText("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_BlankEmailAndPassword() {
        User user = negativeBlankEmailAndPassword();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.isAlertContainsText("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_OnlySpacesEmail() {
        User user = negativeOnlySpacesEmail();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.isAlertContainsText("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_2AtSignsInEmail() {
        User user = negative2AtSignsInEmail();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.isAlertContainsText("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_2AtSignsLettersBetweenEmail() {
        User user = negative2AtSignsLettersBetweenEmail();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.isAlertContainsText("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_NoLettersBeforeAtSignEmail() {
        User user = negativeNoLettersBeforeAtSignEmail();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.isAlertContainsText("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_NoLettersAfterAtSignEmail() {
        User user = negativeNoLettersAfterAtSignEmail();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.isAlertContainsText("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_OnlyCyrillicLettersEmail_BUG() {
        User user = negativeOnlyCyrillicLettersEmail();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.isAlertContainsText("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_LatinAndCyrillicLettersEmail_BUG() {
        User user = negativeLatinAndCyrillicLettersEmail();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.isAlertContainsText("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_blankPassword() {
        User user = negativeBlankPassword();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.isAlertContainsText("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_onlySpacesPassword() {
        User user = negativeOnlySpacesPassword();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.isAlertContainsText("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_noSpecialCharsPassword() {
        Faker faker = new Faker();
        User user = new User(faker.internet().emailAddress(), "Password123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.isAlertContainsText("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_wrongSpecialCharPassword() {
        User user = negativeNoSpecialCharsPassword();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.isAlertContainsText("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_cyrillicLettersPassword() {
        User user = negativeCyrillicLettersPassword();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.isAlertContainsText("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_cyrillicAndEnglishLettersPassword_BUG() {
        User user = negativeCyrillicAndEnglishLettersPassword();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.isAlertContainsText("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_latinWithSerifPassword_BUG() {
        User user = negativeLatinWithSerifPassword();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.isAlertContainsText("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_noUppercasePassword() {
        User user = negativeNoUppercasePassword();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.isAlertContainsText("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_noLowercasePassword() {
        User user = negativeNoLowercasePassword();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.isAlertContainsText("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_noNumbersPassword() {
        User user = negativeNoNumbersPassword();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.isAlertContainsText("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_7SymbolsPassword() {
        User user = negative7SymbolsPassword();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.isAlertContainsText("Email must contains"));
    }

    @Test
    public void registrationNegativeTest_16SymbolsPassword_BUG() {
        User user = negative16SymbolsPassword();
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.isAlertContainsText("Email must contains"));
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
