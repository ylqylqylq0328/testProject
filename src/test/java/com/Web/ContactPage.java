package com.Web;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactPage extends PageObject {

    @FindBy(name = "borrowerFirstName")
    private WebElement borrowerFirstName;

    @FindBy(name = "borrowerLastName")
    private WebElement borrowerLastName;

    @FindBy(id = "borrowerStreet")
    private WebElement borrowerStreet;

    @FindBy(name = "borrowerCity")
    private WebElement borrowerCity;

    @FindBy(name = "borrowerState")
    private WebElement borrowerState;

    @FindBy(name = "borrowerZipCode")
    private WebElement borrowerZipCode;

    @FindBy(name = "borrowerDateOfBirth")
    private WebElement borrowerDateOfBirth;

    @FindBy(css = "button[type='submit']")
    private WebElement continueButton;

    @FindBy(css = "div[data-error='true']")
    private WebElement ErrorMessage;

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public void enterContactInfo(String borrowerFirstName,
                                 String borrowerLastName,
                                 String borrowerStreet,
                                 String borrowerCity,
                                 String borrowerState,
                                 String borrowerZipCode,
                                 String borrowerDateOfBirth) {
        this.borrowerFirstName.sendKeys(borrowerFirstName);
        this.borrowerLastName.sendKeys(borrowerLastName);
        this.borrowerStreet.sendKeys(borrowerStreet);
        this.borrowerCity.sendKeys(borrowerCity);
        this.borrowerState.sendKeys(borrowerState);
        this.borrowerZipCode.sendKeys(borrowerZipCode);
        this.borrowerDateOfBirth.sendKeys(borrowerDateOfBirth);
    }

    public IncomePage submit(){
        this.borrowerStreet.sendKeys(Keys.ENTER);
        this.continueButton.click();
        if (driver.getCurrentUrl().contains("income")){
            return new IncomePage(driver);}
        else
            return null;
    }

    public String getErrorMessage(){
        return this.ErrorMessage.getText();
    }

}
