package ui_tests;

import data_providers.ContactDataProvider;
import dto.Contact;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.HeaderMenuItem;

import static org.openqa.selenium.devtools.v85.debugger.Debugger.pause;
import static pages.BasePage.clickButtonHeader;
import static utils.ContactFactory.*;


public class AddNewContactTests extends AppManager {
    HomePage homePage;
    LoginPage loginPage;
    ContactsPage contactsPage;
    AddContactPage addContactPage;
    int countOfContacts;
    SoftAssert softAssert = new SoftAssert();

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

    @Test
    public void addNewContactPositiveTest_WithContactsList() {
        addContactPage.typeContactForm(positiveContact());
        addContactPage.clickBtnSaveContact();
        int countOfContactsAfterAdd = contactsPage.getContactsCount();
        Assert.assertEquals(countOfContactsAfterAdd, countOfContacts + 1);
    }

    @Test
    public void addNewContactPositiveTest_WithLastContact() {
        Contact contact = positiveContact();
        addContactPage.typeContactForm(contact);
        addContactPage.clickBtnSaveContact();
//        contactsPage.clickLastContact();
        Assert.assertTrue(contactsPage.isContactPresent(contact));
    }
// ==============================HW8===================================

    @Test
    public void addNewContactPositiveTest_WithScrollToLastContact() {
        Contact contact = positiveContact();
        addContactPage.typeContactForm(contact);
        addContactPage.clickBtnSaveContact();
        contactsPage.scrollToLastContact();
//        Assert.assertTrue(contactsPage.isLastContactCorrect(contact));
    }

    @Test
    public void addNewContactPositiveTest_WithXYScrollToLastContact() {
        Contact contact = positiveContact();
        addContactPage.typeContactForm(contact);
        addContactPage.clickBtnSaveContact();
        contactsPage.scrollToLastContact_WithXY();
        softAssert.assertTrue(contactsPage.isTextInLastContactNamePresent(contact.getName()));
        softAssert.assertTrue(contactsPage.isTextInLastContactPhonePresent(contact.getPhone()));
        softAssert.assertAll();
    }

// ==============================CW10===================================
@Test
public void addNewContactPositiveTest_ComparisonWithLastContactCard(){
    Contact contact = positiveContact();
    addContactPage.typeContactForm(contact);
    addContactPage.clickBtnSaveContact();
    contactsPage.scrollToLastContact();
    contactsPage.clickLastContact();
    String text = contactsPage.getContactText();
//    System.out.println(text);
    softAssert.assertTrue(text.contains(contact.getName()), "name validation");
    softAssert.assertTrue(text.contains(contact.getEmail()), "email validation");
    softAssert.assertTrue(text.contains(contact.getPhone()), "phone validation");
    softAssert.assertAll();
}

@Test(dataProvider = "dataProviderFromFile", dataProviderClass = ContactDataProvider.class)
    public void addContact_WithDataProvider (Contact contact){
        addContactPage.typeContactForm(contact);
        addContactPage.clickBtnSaveContact();
        int countOfContactsAfterAdd = contactsPage.getContactsCount();
        Assert.assertEquals(countOfContactsAfterAdd, countOfContacts + 1);
}

    //==================================HW9========================

    //последний тест здесь должен падать, т.к. сохранение контакта с пустым email - это баг
    @Test(dataProvider = "dataProviderBtnSaveInactive", dataProviderClass = ContactDataProvider.class)
    public void addContactNegative_BtnSaveDisabled(Contact contact){
        addContactPage.typeContactForm(contact);
        addContactPage.clickBtnSaveContact();
        Assert.assertTrue(addContactPage.isBtnSaveContactDisplayed());
    }

    //здесь 1 тоже должен падать - сохранение мейла без точки тоже баг
    @Test(dataProvider = "dataProviderEmptyFields_AlertPresent", dataProviderClass = ContactDataProvider.class)
    public void addContactNegative_Alert(Contact contact){
        addContactPage.typeContactForm(contact);
        addContactPage.clickBtnSaveContact();
        Assert.assertTrue(addContactPage.closeAlertReturnText().contains("not valid"));
    }

    //==================================CW11========================

    @Test(dataProvider = "dataProvider_WrongPhone", dataProviderClass = ContactDataProvider.class)
    public void addNewContactNegative_WrongPhone(Contact contact){
        addContactPage.typeContactForm(contact);
        addContactPage.clickBtnSaveContact();
        Assert.assertTrue(addContactPage.closeAlertReturnText().contains("Phone not valid:"));
    }

    @Test(dataProvider = "dataProvider_EmptyFields", dataProviderClass = ContactDataProvider.class)
    public void addNewContactNegative_EmptyFields(Contact contact){
        addContactPage.typeContactForm(contact);
        addContactPage.clickBtnSaveContact();
        Assert.assertTrue(addContactPage.isBtnSaveContactDisplayed());
    }







}
