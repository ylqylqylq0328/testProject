package com.Web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IncomePage extends PageObject {

    @FindBy(name = "borrowerIncome")
    private WebElement borrowerIncome;

    @FindBy(name = "borrowerAdditionalIncome")
    private WebElement borrowerAdditionalIncome;

    @FindBy(css = "button[type='submit']")
    private WebElement continueButton;

    public IncomePage(WebDriver driver) {
        super(driver);
    }

    public void enterIncomeInfo(String borrowerIncome,
                                String borrowerAdditionalIncome) {
        this.borrowerIncome.clear();
        this.borrowerAdditionalIncome.clear();
        this.borrowerIncome.sendKeys(borrowerIncome);
        this.borrowerAdditionalIncome.sendKeys((borrowerAdditionalIncome));
    }

    public LoginPage submit(){
        this.continueButton.click();
        this.continueButton.click();
        return new LoginPage(driver);
    }
}
