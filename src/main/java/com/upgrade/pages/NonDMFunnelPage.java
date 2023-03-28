package com.upgrade.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NonDMFunnelPage extends BasePage {

    public NonDMFunnelPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "desiredAmount")
    private WebElement loanAmount;

    @FindBy(css = "[data-auto='dropLoanPurpose']")
    private WebElement loanPurposeDropDown;

    @FindBy(css = "[data-auto='CheckYourRate']")
    private WebElement checkYourRateButton;

    public void enterLoanAmount(String loanValue) {
        inputValue(loanAmount, loanValue);
    }

    public void selectLoanPurposeFromDropDown(String loanPurpose) {
        selectFromDropDown(loanPurposeDropDown, loanPurpose);
    }

    public PersonalInfoPage submitCheckingLoanRate() {
        click(checkYourRateButton);
        return new PersonalInfoPage(driver);
    }

    public NonDMFunnelPage navigateToNonDMFunnelPage(String url) {
        driver.navigate().to(url);
        waitForWebElemetToBePresent(loanAmount);
        return this;
    }
}
