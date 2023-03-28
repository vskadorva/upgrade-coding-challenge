package com.upgrade.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        waitForWebElemetToBePresent(username);
    }

    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(css = "[data-auto='login']")
    private WebElement loginButton;

    public void enterUserName(String usernameValue) {
        inputValue(username, usernameValue);
    }

    public void enterPassword(String passwordValue) {
        inputValue(password, passwordValue);
    }

    public OfferPage signIn() {
        click(loginButton);
        return new OfferPage(driver);
    }
}
