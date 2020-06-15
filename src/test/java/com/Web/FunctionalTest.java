package com.Web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class FunctionalTest {

    protected static WebDriver driver;

    @BeforeAll
    public static void beforeAll(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp(){

        driver = new ChromeDriver();
    }

    @AfterEach
    public void cleanUp(){

        driver.close();
    }

}
