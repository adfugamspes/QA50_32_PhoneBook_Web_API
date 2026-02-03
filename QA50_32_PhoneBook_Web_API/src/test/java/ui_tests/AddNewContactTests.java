package ui_tests;

import dto.Contact;
import manager.AppManager;
import org.testng.Assert;
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
    int countOfContacts;

    @BeforeMethod
    public void login() {
        homePage = new HomePage(getDriver());
        loginPage = clickButtonHeader(HeaderMenuItem.LOGIN);
        loginPage.typeLoginRegistrationForm("testmail123@mail.com", "Password!123");
        loginPage.clickBtnLoginForm();
        contactsPage = new ContactsPage((getDriver()));
        countOfContacts = contactsPage.getContactsCount();
        addContactPage = clickButtonHeader(HeaderMenuItem.ADD);
    }

    // ======================= CW9=============================

    @Test
    public void addNewContactPositiveTest_WithContactsList(){
        addContactPage.typeContactForm(positiveContact());
        addContactPage.clickBtnSaveContact();
        int countOfContactsAfterAdd = contactsPage.getContactsCount();
        Assert.assertEquals(countOfContactsAfterAdd, countOfContacts + 1);
    }

    @Test
    public void addNewContactPositiveTest_WithLastContact(){
        Contact contact = positiveContact();
        addContactPage.typeContactForm(contact);
        addContactPage.clickBtnSaveContact();
//        contactsPage.clickLastContact();
        Assert.assertTrue(contactsPage.isContactPresent(contact));
    }
}
