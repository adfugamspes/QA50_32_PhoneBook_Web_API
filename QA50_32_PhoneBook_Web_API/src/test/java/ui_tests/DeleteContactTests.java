package ui_tests;

import manager.AppManager;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.HeaderMenuItem;
import data_providers.ContactDataProvider.*;

import static pages.BasePage.clickButtonHeader;

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
        loginPage.typeLoginRegistrationForm("testmail123@mail.com", "Password!123");
        loginPage.clickBtnLoginForm();
        contactsPage = new ContactsPage((getDriver()));
   }

    @Test
    public void deleteContactByIndexPositiveTest(){
        int countBefore = contactsPage.getContactsCount();
        String contactToDelete = contactsPage.getContactTextByIndex(5);
        String deletedContact = contactsPage.deleteContactByIndex(5);
        int countAfter = contactsPage.getContactsCount();
        softAssert.assertEquals(countAfter, countBefore - 1);
        softAssert.assertEquals(contactToDelete, deletedContact);
        softAssert.assertFalse(contactsPage.isContactPresent(deletedContact));
        softAssert.assertAll();
    }

    //==============================CW14===============================
    @Test
    public void deleteFirstContact(){
        countOfContacts = contactsPage.getContactsCount();
        contactsPage.deleteFirstContact();
        contactsPage.pause(3);
        int countContactsAfterDelete = contactsPage.getContactsCount();
        Assert.assertEquals(countContactsAfterDelete, countOfContacts - 1);
    }

}
