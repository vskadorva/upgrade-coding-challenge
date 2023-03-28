package com.upgrade.pages;

import org.openqa.selenium.*;
import lombok.NonNull;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    protected void click(@NonNull WebElement element) {
        element.click();
    }

    protected void inputValue(@NonNull WebElement element, @NonNull String value) {
        element.sendKeys(value);
    }

    protected void selectFromDropDown(@NonNull WebElement element, @NonNull String value) {
        Select dropDown = new Select(element);
        for (WebElement option : dropDown.getOptions()) {
            if (option.getText().equals(value)) {
                dropDown.selectByVisibleText(option.getText());
                break;
            }
        }
    }

    protected void waitForWebElemetToBePresent(WebElement elementToWait) {
        System.out.println("Waiting for web element " + elementToWait + " to be present...");

        WebDriverWait wait = new WebDriverWait(driver, 15);

        try {
            wait
                    .ignoring(StaleElementReferenceException.class)
                    .withTimeout(Duration.ofSeconds(60))
                    .pollingEvery(Duration.ofSeconds(1))
                    .until(visibilityOf(elementToWait));

        } catch (TimeoutException e) {
            System.out.println("Cannot see element: " + elementToWait);
            throw new RuntimeException(e);
        }
    }

    public static void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            System.out.println(e.toString());
            Thread.currentThread().interrupt();
        }
    }

    public void blur() {
        ((JavascriptExecutor) driver).executeScript("!!document.activeElement ? document.activeElement.blur() : 0");
    }

    public void forceClick(@NonNull WebElement element) {
        JavascriptExecutor executor = ((JavascriptExecutor) driver);
        executor.executeScript("arguments[0].click();", element);
    }
}
