package com.upgrade.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignOutPage extends BasePage {
    public SignOutPage(WebDriver driver) {
        super(driver);
        waitForWebElemetToBePresent(signOutText);
    }

    @FindBy(css = "[data-auto='logoutMessage']")
    private WebElement signOutText;

    public String getSignOutText() {
        return signOutText.getText();
    }

    public LoginPage navigateToLoginAfterSignOut(String loginUrl) {
        driver.navigate().to(loginUrl);
        return new LoginPage(driver);
    }
}
