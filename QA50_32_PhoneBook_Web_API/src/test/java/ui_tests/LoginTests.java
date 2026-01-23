package ui_tests;

import dto.User;
import manager.AppManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;

public class LoginTests extends AppManager {

    @Test
    public void loginPositiveTest () {
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
    public void loginPositiveTestWithUser () {
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
    public void loginNegativeTestWrongEmail(){
        User user = new User("testmail123ail.com", "Password!123");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }



}
