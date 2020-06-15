package com.Web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class OfferPage extends PageObject {

    @FindBy(css = "span[data-auto='userLoanAmount']")
    private WebElement userLoanAmount;

    @FindBy(css = "span[data-auto='defaultMonthlyPayment']")
    private WebElement monthlyPayment;

    @FindBy(css = "div[data-auto='defaultLoanTerm']")
    private WebElement monthTerm;

    @FindBy(xpath = "//div[@data-auto='defaultLoanInterestRate']")
    private WebElement interestRate;

    @FindBy(xpath = "//div[@data-auto='defaultMoreInfoAPR']/div")
    private WebElement apr;

    @FindBy(css = "label[for='header-nav-toggle']")
    private WebElement OfferMenu;

    @FindBy(css = "a[href='/funnel/logout']" )
    private WebElement SignOut;

    public OfferPage(WebDriver driver) {
        super(driver);
    }

    public void signOut() {
        new Actions(driver).moveToElement(OfferMenu).click();
        new Actions(driver).moveToElement(SignOut).click();
    }

    public List<String> getOfferInfo(){
        List<String> pageInfo = new ArrayList<String>();
        pageInfo.add(this.userLoanAmount.getText());
        pageInfo.add(this.monthlyPayment.getText());
        pageInfo.add(this.monthTerm.getText());
        pageInfo.add(this.interestRate.getText());
        pageInfo.add(this.apr.getText());
        return pageInfo;
    }
}
