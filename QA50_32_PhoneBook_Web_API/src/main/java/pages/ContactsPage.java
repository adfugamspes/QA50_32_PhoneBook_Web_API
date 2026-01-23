package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

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

    public boolean isBtnSignOutDisplayed() {
        return isElementDisplayed(btnSignOut);
    }

    public boolean isBtnAddContactDisplayed() {
        return isElementDisplayed(btnAddContact);
    }

    public boolean isTextInBtnSignOutPresent(String text){
        return isTextInElementPresent(btnSignOut, text);
    }

    public boolean isTextInBtnAddPresent(String text){
        return isTextInElementPresent(btnAddContact, text);
    }

    public boolean isTextInContactPageMessagePresent (String text){
        return isTextInElementPresent(contactPageMessage, text);
    }
}
