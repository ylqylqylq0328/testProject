package com.Web;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class NonDMFunnelPage extends PageObject {

    @FindBy(name = "desiredAmount")
    private WebElement desiredAmount;

    @FindBy(css = "select[data-auto='dropLoanPurpose']")
    private WebElement dropLoadPurpose;

    @FindBy(css = "button[data-auto='CheckYourRate']")
    private WebElement CheckYourRate;

    @FindBy(css = "div[data-error='true']")
    private WebElement ErrorMessage;



    public NonDMFunnelPage(WebDriver driver) {
        super(driver);
    }

    public void enterDesiredAmount(String amount) {
        this.desiredAmount.clear();
        this.desiredAmount.sendKeys(amount);
        this.desiredAmount.sendKeys(Keys.ENTER);
    }

    public void chooseDropLoadPurpose(String purpose){
        Select selectDropLoadPurpose = new Select(this.dropLoadPurpose);
        selectDropLoadPurpose.selectByVisibleText(purpose);
    }

    public ContactPage submit(){
        CheckYourRate.click();
        if (driver.getCurrentUrl().contains("contact")){
            return new ContactPage(driver);}
        else
            return null;
    }

    public String getErrorMessage(){
        return this.ErrorMessage.getText();
    }

    public String getLoadAmount(){
        return this.desiredAmount.getAttribute("value");
    }
}
