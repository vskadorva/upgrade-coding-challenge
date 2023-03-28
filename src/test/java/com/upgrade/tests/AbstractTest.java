package com.upgrade.tests;

import com.upgrade.utils.ApiCaller;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class AbstractTest {
    private WebDriver driver;
    private ApiCaller apiCaller;

    @BeforeClass
    public void beforeTest() {
        if (driver != null) {
            driver.manage().window().maximize();
        }
    }

    @AfterClass
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        return driver;
    }

    public ApiCaller apiCaller() {
        if (apiCaller == null) {
            apiCaller = new ApiCaller();
        }
        return apiCaller;
    }

}
