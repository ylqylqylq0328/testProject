package com.Web;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.Calendar;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OfferInfoTest extends FunctionalTest{

    final String urlInit = "https://www.credify.tech/funnel/nonDMFunnel";
    final String urlLogin = "https://www.credify.tech/portal/login";
    //final String username = "candidate02@upgrade-challenge.com";
    final String usernamePart1 = "candidate";
    final String usernamePart2 = "@upgrade-challenge.com";
    final String password = "Nn123456";

    final static String LoanAmountErrorMessage = "Please enter loan amount between $1,000 and $35,000.";
    final static String MissingFieldMessage = "This field is required";
    final static String MissingLoadPurposeMessage = "Select a purpose";
    final static String InvalidZipCode = "Please enter a valid zip code";
    final static String InvalidDate = "Please enter a valid date.";
    final static String AgeLes018 = "You must be at least 18 years old.";
    final static String InvalidEmail = "Please enter a valid email.";
    final static String InvalidPassword = "Password must be at least 8 characters long, contain at least one number, one uppercase letter and one lowercase letter.";
    final static String MissingAgreement = "You need to agree by electronic signature in order to continue";

    @ParameterizedTest
    @ValueSource(strings = {"Business","Home Improvement","Large Purchase","Pay off Credit Cards","Debt Consolidation","Other"})
    public void testOfferInfo(){
        Random random = new Random();
        String username = usernamePart1 + String.valueOf(random.nextInt(100)) + usernamePart2;
        List<String> expectedOfferInfo = getInfoProcess("2000","Business","FirstN","LastN","abc","New York","NY","12345","10/10/1988","110000","5000",username,password);
        List<String> actuallyOfferInfo = queryInfoProcess(username,password);
        assertEquals(expectedOfferInfo,actuallyOfferInfo);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/invalidAmount.csv", delimiter = ';',numLinesToSkip = 1)
    public void testInvalidLoanAmount(String amount, String message){
        driver.get(urlInit);
        driver.manage().window().maximize();
        NonDMFunnelPage nonDMFunnelPage = new NonDMFunnelPage(driver);
        if (amount.equals("NULL")) {
            amount = "";
        }
        nonDMFunnelPage.enterDesiredAmount(amount);
        assertEquals(message,nonDMFunnelPage.getErrorMessage());
    }

    @Test
    public void testLoanAmountMore35000(){
        driver.get(urlInit);
        driver.manage().window().maximize();
        NonDMFunnelPage nonDMFunnelPage = new NonDMFunnelPage(driver);
        nonDMFunnelPage.enterDesiredAmount("35001");
        assertEquals("35,000",nonDMFunnelPage.getLoadAmount());
    }

    @Test
    public void testMissingLoadPurpose(){
        driver.get(urlInit);
        driver.manage().window().maximize();
        NonDMFunnelPage nonDMFunnelPage = new NonDMFunnelPage(driver);
        nonDMFunnelPage.enterDesiredAmount("1000");
        nonDMFunnelPage.submit();
        assertEquals(MissingLoadPurposeMessage,nonDMFunnelPage.getErrorMessage());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/emptyContactInfo.csv", delimiter = ';',numLinesToSkip = 1)
    public void testMissingContactInfoFirstName(String firstName,String lastName, String street, String city, String state) {
        driver.get(urlInit);
        driver.manage().window().maximize();
        NonDMFunnelPage nonDMFunnelPage = new NonDMFunnelPage(driver);
        nonDMFunnelPage.enterDesiredAmount("2000");
        nonDMFunnelPage.chooseDropLoadPurpose("Large Purchase");
        ContactPage contactPage = nonDMFunnelPage.submit();
        if (firstName.equals("NULL")) firstName="";
        if (lastName.equals("NULL")) lastName="";
        if (street.equals("NULL")) street="";
        if (city.equals("NULL")) city="";
        if (state.equals("NULL")) state="";
        contactPage.enterContactInfo(firstName,lastName,street,city,state, "12345", "10/10/1988");
        contactPage.submit();
        assertEquals(MissingFieldMessage,contactPage.getErrorMessage());
    }

    @Test
    public void testZipCodeOnlyDigital() {
        driver.get(urlInit);
        driver.manage().window().maximize();
        NonDMFunnelPage nonDMFunnelPage = new NonDMFunnelPage(driver);
        nonDMFunnelPage.enterDesiredAmount("2000");
        nonDMFunnelPage.chooseDropLoadPurpose("Pay off Credit Cards");
        ContactPage contactPage = nonDMFunnelPage.submit();
        contactPage.enterContactInfo("FirstN", "LastN", "abc", "New York", "NY", "ABC", "10/10/1988");
        contactPage.submit();
        assertEquals(MissingFieldMessage,contactPage.getErrorMessage());
    }

    @Test
    public void testInvalidZipCode() {
        driver.get(urlInit);
        driver.manage().window().maximize();
        NonDMFunnelPage nonDMFunnelPage = new NonDMFunnelPage(driver);
        nonDMFunnelPage.enterDesiredAmount("2000");
        nonDMFunnelPage.chooseDropLoadPurpose("Other");
        ContactPage contactPage = nonDMFunnelPage.submit();
        contactPage.enterContactInfo("FirstN", "LastN", "abc", "New York", "NY", "1234", "10/10/1988");
        contactPage.submit();
        assertEquals(InvalidZipCode,contactPage.getErrorMessage());
    }

    @Test
    public void testInvalidBirthdayMonth() {
        driver.get(urlInit);
        driver.manage().window().maximize();
        NonDMFunnelPage nonDMFunnelPage = new NonDMFunnelPage(driver);
        nonDMFunnelPage.enterDesiredAmount("2000");
        nonDMFunnelPage.chooseDropLoadPurpose("Business");
        ContactPage contactPage = nonDMFunnelPage.submit();
        contactPage.enterContactInfo("FirstN", "LastN", "abc", "New York", "NY", "12345", "13/10/1988");
        contactPage.submit();
        assertEquals(InvalidDate,contactPage.getErrorMessage());
    }

    @Test
    public void testInvalidBirthdayDay() {
        driver.get(urlInit);
        driver.manage().window().maximize();
        NonDMFunnelPage nonDMFunnelPage = new NonDMFunnelPage(driver);
        nonDMFunnelPage.enterDesiredAmount("2000");
        nonDMFunnelPage.chooseDropLoadPurpose("Home Improvement");
        ContactPage contactPage = nonDMFunnelPage.submit();
        contactPage.enterContactInfo("FirstN", "LastN", "abc", "New York", "NY", "12345", "12/32/1988");
        contactPage.submit();
        assertEquals(InvalidDate,contactPage.getErrorMessage());
    }

    @Test
    @Disabled("Have To Fix this Bug, it should be invalid value before 1930")
    public void testBirthdayDayBefore1930() {
        driver.get(urlInit);
        driver.manage().window().maximize();
        NonDMFunnelPage nonDMFunnelPage = new NonDMFunnelPage(driver);
        nonDMFunnelPage.enterDesiredAmount("2000");
        nonDMFunnelPage.chooseDropLoadPurpose("Home Improvement");
        ContactPage contactPage = nonDMFunnelPage.submit();
        contactPage.enterContactInfo("FirstN", "LastN", "abc", "New York", "NY", "12345", "01/01/1900");
        contactPage.submit();
        assertEquals(InvalidDate,contactPage.getErrorMessage());
    }

    @Test
    @Disabled("Have To Fix this Bug, it should be invalid value after 2000")
    public void testBirthdayDayAfter2000() {
        driver.get(urlInit);
        driver.manage().window().maximize();
        NonDMFunnelPage nonDMFunnelPage = new NonDMFunnelPage(driver);
        nonDMFunnelPage.enterDesiredAmount("2000");
        nonDMFunnelPage.chooseDropLoadPurpose("Home Improvement");
        ContactPage contactPage = nonDMFunnelPage.submit();
        contactPage.enterContactInfo("FirstN", "LastN", "abc", "New York", "NY", "12345", "12/31/2001");
        contactPage.submit();
        assertEquals(InvalidDate,contactPage.getErrorMessage());
    }

    @Test
    public void testAgeLess18() {
        driver.get(urlInit);
        driver.manage().window().maximize();
        NonDMFunnelPage nonDMFunnelPage = new NonDMFunnelPage(driver);
        nonDMFunnelPage.enterDesiredAmount("2000");
        nonDMFunnelPage.chooseDropLoadPurpose("Home Improvement");
        ContactPage contactPage = nonDMFunnelPage.submit();
        String birthday = "01/01/" +  String.valueOf(Calendar.getInstance().get(Calendar.YEAR) -16);
        contactPage.enterContactInfo("FirstN", "LastN", "abc", "New York", "NY", "12345", birthday);
        contactPage.submit();
        assertEquals(AgeLes018,contactPage.getErrorMessage());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/invalidLogin.csv", delimiter = ';',numLinesToSkip = 1)
    public void testInvalidLoginPage(String username, String password, String agreement, String message) {
        driver.get(urlInit);
        driver.manage().window().maximize();
        NonDMFunnelPage nonDMFunnelPage = new NonDMFunnelPage(driver);
        nonDMFunnelPage.enterDesiredAmount("2000");
        nonDMFunnelPage.chooseDropLoadPurpose("Business");
        ContactPage contactPage = nonDMFunnelPage.submit();
        contactPage.enterContactInfo("FirstN","LastN","abc","New York","NY","12345","10/10/1988");
        IncomePage incomePage = contactPage.submit();
        incomePage.enterIncomeInfo("110000","5000");
        LoginPage loginPage = incomePage.submit();
        loginPage.enterUserInfo(username,password);
        if (agreement.equals("Y")) {
            loginPage.clickAgreements();
        }
        loginPage.submit();
        assertEquals(message,loginPage.getErrorMessage());
    }

    private List<String> getInfoProcess(String DesiredAmount,
                                        String DropLoadPurpose,
                                        String FistName,
                                        String LastName,
                                        String Street,
                                        String City,
                                        String State,
                                        String ZipCode,
                                        String DateOfBirth,
                                        String Income,
                                        String AdditionalIncome,
                                        String Username,
                                        String Password
                                        )
    {
        driver.get(urlInit);
        driver.manage().window().maximize();
        NonDMFunnelPage nonDMFunnelPage = new NonDMFunnelPage(driver);
        nonDMFunnelPage.enterDesiredAmount(DesiredAmount);
        nonDMFunnelPage.chooseDropLoadPurpose(DropLoadPurpose);
        ContactPage contactPage = nonDMFunnelPage.submit();
        contactPage.enterContactInfo(FistName,LastName,Street,City,State,ZipCode,DateOfBirth);
        IncomePage incomePage = contactPage.submit();
        incomePage.enterIncomeInfo(Income,AdditionalIncome);
        LoginPage loginPage = incomePage.submit();
        loginPage.enterUserInfo(Username,Password);
        loginPage.clickAgreements();
        OfferPage offerPage = loginPage.submit();
        List<String> expectedOfferInfo = offerPage.getOfferInfo();
        offerPage.signOut();
        return expectedOfferInfo;
    }

    private List<String> queryInfoProcess(String Username, String Password){
        driver.get(urlLogin);
        driver.manage().window().maximize();
        LoginPage newLoginPage = new LoginPage(driver);
        newLoginPage.enterUserInfo(Username,Password);
        newLoginPage.clickAgreements();
        OfferPage newOfferPage = newLoginPage.submit();
        List<String> actuallyOfferInfo = newOfferPage.getOfferInfo();
        newOfferPage.signOut();
        return actuallyOfferInfo;
    }
}
