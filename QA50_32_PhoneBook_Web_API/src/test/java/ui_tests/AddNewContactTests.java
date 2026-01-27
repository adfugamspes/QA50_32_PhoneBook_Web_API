package ui_tests;

import manager.AppManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.HeaderMenuItem;
import static pages.BasePage.clickButtonHeader;
import static utils.ContactFactory.*;


public class AddNewContactTests extends AppManager {
    HomePage homePage;
    LoginPage loginPage;
    ContactsPage contactsPage;
    AddContactPage addContactPage;

    @BeforeMethod
    public void login() {
        homePage = new HomePage(getDriver());
        loginPage = clickButtonHeader(HeaderMenuItem.LOGIN);
        loginPage.typeLoginRegistrationForm("testmail123@mail.com", "Password!123");
        loginPage.clickBtnLoginForm();
        contactsPage = new ContactsPage((getDriver()));
        addContactPage = clickButtonHeader(HeaderMenuItem.ADD);
    }

    @Test
    public void addNewContactPositiveTest(){
        addContactPage.typeContactForm(positiveContact());
    }
}
