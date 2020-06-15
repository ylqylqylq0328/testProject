package com.Web;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends PageObject{

    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(css = "input[type='checkbox']")
    private WebElement checkOption;

    @FindBy(css = "button[type='submit']")
    private WebElement continueButton;

    @FindBy(css = "div[data-error='true']")
    private WebElement ErrorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUserInfo(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
        this.username.clear();
        this.password.clear();
        this.username.sendKeys(username);
        this.password.sendKeys((password));
    }

    public void clickAgreements() {
        Actions actions = new Actions(driver);
        actions.moveToElement(checkOption).click().perform();
    }

    public OfferPage submit() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        this.continueButton.click();
        try {
            ErrorMessage.isEnabled();
            return null;
        }
        catch (NoSuchElementException ex) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[data-auto='userLoanAmount']")));
            return new OfferPage(driver);
        }
    }

    public String getErrorMessage(){
        return this.ErrorMessage.getText();
    }

}
