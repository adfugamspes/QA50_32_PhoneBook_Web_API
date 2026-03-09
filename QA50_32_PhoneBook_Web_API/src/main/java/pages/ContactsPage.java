package pages;

import dto.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ContactsPage extends BasePage {

    public ContactsPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//button[text()='Sign Out']")
    WebElement btnSignOut;

    @FindBy(xpath = "//a[text()='ADD']")
    WebElement btnAddContact;

    @FindBy(xpath = "//h1[text()=' No Contacts here!']")
    WebElement contactPageMessage;

    @FindBy(className = "contact-item_card__2SOIM")
    List<WebElement> contactsList;

    @FindBy(xpath = "//div[@class='contact-item_card__2SOIM'][last()]")
    WebElement lastContact;

    @FindBy(xpath = "//div[@class='contact-item_card__2SOIM']")
    WebElement firstContact;

    @FindBy(xpath = "//div[@class='contact-page_leftdiv__yhyke']/div")
    WebElement divContactsList;

    @FindBy(xpath = "//div[@class='contact-item_card__2SOIM'][last()]//h2")
    WebElement lastContactName;

    @FindBy(xpath = "//div[@class='contact-item_card__2SOIM'][last()]//h3")
    WebElement lastContactPhone;

    @FindBy(xpath = "//div[@class='contact-item-detailed_card__50dTS']")
    WebElement itemDetailCard;

    @FindBy(xpath = "//button[text()='Remove']")
    WebElement btnRemoveContact;

    @FindBy(xpath = "//button[text()='Edit']")
    WebElement btnEditContact;

    @FindBy(xpath = "//div[@class='form_form__FOqHs']/input[1]")
    WebElement inputName;

    @FindBy(xpath = "//div[@class='form_form__FOqHs']/input[2]")
    WebElement inputLastName;
    @FindBy(xpath = "//div[@class='form_form__FOqHs']/input[3]")
    WebElement inputPhone;
    @FindBy(xpath = "//div[@class='form_form__FOqHs']/input[4]")
    WebElement inputEmail;
    @FindBy(xpath = "//div[@class='form_form__FOqHs']/input[5]")
    WebElement inputAddress;
    @FindBy(xpath = "//div[@class='form_form__FOqHs']/input[6]")
    WebElement inputDescription;
    @FindBy(xpath = "//button[text()='Save']")
    WebElement btnSave;

    public boolean isBtnSignOutDisplayed() {
        return isElementDisplayed(btnSignOut);
    }

    public boolean isBtnAddContactDisplayed() {
        return isElementDisplayed(btnAddContact);
    }

    public boolean isTextInBtnSignOutPresent(String text) {
        return isTextInElementPresent(btnSignOut, text);
    }

    public boolean isTextInBtnAddPresent(String text) {
        return isTextInElementPresent(btnAddContact, text);
    }

    public boolean isTextInContactPageMessagePresent(String text) {
        return isTextInElementPresent(contactPageMessage, text);
    }

    public void clickBtnSignOut() {
        btnSignOut.click();
    }

    public int getContactsCount() {
        return contactsList.size();
    }

    public void clickLastContact() {
        lastContact.click();
    }

    public boolean isContactPresent(Contact contact) {
        for (WebElement element : contactsList) {
            if (element.getText().contains(contact.getName()) || element.getText().contains(contact.getPhone())) {
                System.out.println(element.getText());
                return true;
            }
        }
        return false;
    }

    public boolean isContactPresent(String text) {
        for (WebElement element : contactsList) {
            if (element.getText().contains(text)) {
                System.out.println(element.getText());
                return true;
            }
        }
        return false;
    }

    public void scrollToLastContact() {
        Actions actions = new Actions(driver);
        actions.scrollToElement(lastContact).perform();
    }

    public void scrollToLastContact_WithXY() {
        Actions actions = new Actions(driver);
        int deltaY = divContactsList.getSize().getHeight();
        System.out.println("Height = " + deltaY);
        WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(contactsList.get(0));
        pause(3);
        actions.scrollFromOrigin(scrollOrigin, 0, deltaY).perform();
    }

    public boolean isLastContactCorrect(Contact contact) {
        return lastContactName.getText().equals(contact.getName()) &&
                lastContactPhone.getText().equals(contact.getPhone());
    }

    public boolean isTextInLastContactNamePresent(String text) {
        return isTextInElementPresent(lastContactName, text);
    }

    public boolean isTextInLastContactPhonePresent(String text) {
        return isTextInElementPresent(lastContactPhone, text);
    }

    public String getContactText() {
        return itemDetailCard.getText();
    }

//====================CW14==============================================

    public void deleteFirstContact() {
        contactsList.get(0).click();
        btnRemoveContact.click();
    }

    public void typeEditForm(Contact contact) {
        contactsList.get(0).click();
        btnEditContact.click();
        inputName.clear();
        inputName.sendKeys(contact.getName());
        inputLastName.clear();
        inputLastName.sendKeys(contact.getLastName());
        inputPhone.clear();
        inputPhone.sendKeys(contact.getPhone());
        inputEmail.clear();
        inputEmail.sendKeys(contact.getEmail());
//        inputEmail.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputAddress.clear();
        inputAddress.sendKeys(contact.getAddress());
        inputDescription.clear();
        inputDescription.sendKeys(contact.getDescription());
        btnSave.click();
    }
}

