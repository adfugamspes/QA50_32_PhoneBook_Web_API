package ui_tests;

import dto.Contact;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.ContactFactory;
import utils.HeaderMenuItem;
import utils.PropertiesReader;
import static pages.BasePage.clickButtonHeader;

public class EditContactTests extends AppManager {
    HomePage homePage;
    LoginPage loginPage;
    ContactsPage contactsPage;
    int countOfContacts;

    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToContactsPage() {
        homePage = new HomePage(getDriver());
        loginPage = clickButtonHeader(HeaderMenuItem.LOGIN);
        loginPage.typeLoginRegistrationForm("testmail123@mail.com", "Password!123");
        loginPage.clickBtnLoginForm();
        contactsPage = new ContactsPage((getDriver()));
        countOfContacts = contactsPage.getContactsCount();
    }

    @Test
    public void editFirstContactPositiveTest(){
        Contact contact = ContactFactory.positiveContact();
        contactsPage.typeEditForm(contact);
        contactsPage.pause(3);
        Assert.assertTrue(contactsPage.isContactPresent(contact));
    }

}
