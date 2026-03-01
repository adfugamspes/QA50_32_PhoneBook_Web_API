package ui_tests;

import dto.Contact;
import manager.AppManager;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.HeaderMenuItem;
import data_providers.ContactDataProvider.*;
import utils.TestNGListener;
import static utils.PropertiesReader.*;

import static pages.BasePage.clickButtonHeader;
@Listeners({TestNGListener.class})

public class DeleteContactTests extends AppManager {

    HomePage homePage;
    LoginPage loginPage;
    ContactsPage contactsPage;
    int countOfContacts;

    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToContactsPage() {
        homePage = new HomePage(getDriver());
        loginPage = clickButtonHeader(HeaderMenuItem.LOGIN);
        loginPage.typeLoginRegistrationForm(getProperty("base.properties", "login"), getProperty("base.properties", "password"));
        loginPage.clickBtnLoginForm();
        contactsPage = new ContactsPage((getDriver()));
        countOfContacts = contactsPage.getContactsCount();
   }

//==============================CW14===============================//

    @Test
    public void deleteFirstContactPositiveTest(){
        contactsPage.deleteFirstContact();
        contactsPage.pause(3);
        int countContactsAfterDelete = contactsPage.getContactsCount();
        Assert.assertEquals(countContactsAfterDelete, countOfContacts - 1);
    }

}
