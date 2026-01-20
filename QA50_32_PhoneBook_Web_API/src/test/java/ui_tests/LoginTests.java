package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends AppManager {

    @Test
    public void loginPositiveTest () {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationForm("testmail123@mail.com", "Password!123");
        loginPage.clickBtnLoginForm();
    }

    @Test
    public void loginPositiveTestWithUser () {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(new User("testmail123@mail.com", "Password!123"));
        loginPage.clickBtnLoginForm();
    }


}
