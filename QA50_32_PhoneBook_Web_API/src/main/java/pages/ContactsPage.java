package pages;

import dto.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

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

    public String getContactTextByIndex(int index){
        WebElement element = contactsList.get(index);
        return element.getText();
    }

    public String deleteContactByIndex(int index) {
        if (index < 0 || index >= contactsList.size())
            throw new IndexOutOfBoundsException();
        WebElement element = contactsList.get(index);
        String deletedContactText = getContactTextByIndex(index);
        contactsList.get(index).click();
        btnRemoveContact.click();
        pause(3);
        return deletedContactText;
    }

    public void editFirstContact(){
        itemDetailCard.click();
        btnEditContact.click();

    }
}

