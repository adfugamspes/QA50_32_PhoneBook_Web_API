package ui_tests;

import dto.Contact;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.ContactFactory;
import utils.HeaderMenuItem;
import utils.PropertiesReader;
import utils.TestNGListener;

import static pages.BasePage.clickButtonHeader;

@Listeners({TestNGListener.class})

public class EditContactTests extends AppManager {
    HomePage homePage;
    LoginPage loginPage;
    ContactsPage contactsPage;
    int countOfContacts;

    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod(alwaysRun = true)
    public void goToContactsPage() {
        homePage = new HomePage(getDriver());
        loginPage = clickButtonHeader(HeaderMenuItem.LOGIN);
        loginPage.typeLoginRegistrationForm("testmail123@mail.com", "Password!123");
        loginPage.clickBtnLoginForm();
        contactsPage = new ContactsPage((getDriver()));
        countOfContacts = contactsPage.getContactsCount();
    }

    @Test(groups = {"smoke", "contact"})
    public void editFirstContactPositiveTest(){
        Contact contact = ContactFactory.positiveContact();
        contactsPage.typeEditForm(contact);
        contactsPage.pause(3);
        Assert.assertTrue(contactsPage.isContactPresent(contact));
    }

    //===========================HW13==================================

    @Test // тест падает, т.к. поле description не редактируется корректно, это баг
    public void editFirstContactPositiveTest_WithCardCheck_BUG(){
        Contact contact = ContactFactory.positiveContact();
        contactsPage.typeEditForm(contact);
        contactsPage.pause(3);
        softAssert.assertTrue(contactsPage.getContactText().contains(contact.getName()), "name validation");
        softAssert.assertTrue(contactsPage.getContactText().contains(contact.getLastName()), "last name validation");
        softAssert.assertTrue(contactsPage.getContactText().contains(contact.getPhone()), "phone validation");
        softAssert.assertTrue(contactsPage.getContactText().contains(contact.getEmail()), "email validation");
        softAssert.assertTrue(contactsPage.getContactText().contains(contact.getAddress()), "address validation");
        softAssert.assertTrue(contactsPage.getContactText().contains(contact.getDescription()), "description validation");
        softAssert.assertAll();
    }

    //===========================CW15==================================

    @Test
    public void editFirstContactPositiveTest_InClass(){
        Contact contact = ContactFactory.positiveContact();
        contactsPage.typeEditForm(contact);
        contactsPage.pause(3);
        String text = contactsPage.getContactText();
        softAssert.assertTrue(text.contains(contact.getName()), "name validation");
        softAssert.assertTrue(text.contains(contact.getPhone()), "phone validation");
        softAssert.assertTrue(text.contains(contact.getEmail()), "email validation");
        softAssert.assertAll();
    }

}
