package com.upgrade.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PersonalIncomePage extends BasePage {
    public PersonalIncomePage(WebDriver driver) {
        super(driver);
        waitForWebElemetToBePresent(individualIncome);
    }

    @FindBy(name = "borrowerIncome")
    private WebElement individualIncome;

    @FindBy(name = "borrowerAdditionalIncome")
    private WebElement additionalIncome;

    @FindBy(css = "[data-auto='continuePersonalInfo']")
    private WebElement continueButton;

    public void enterIndividualIncome(String individualIncomeValue) {
        inputValue(individualIncome, individualIncomeValue);
    }

    public void enterAdditionalIncome(String additionalIncomeValue) {
        inputValue(additionalIncome, additionalIncomeValue);
    }

    public CreateAccountPage continueWithCreateAccount() {
        blur();
        click(continueButton);
        return new CreateAccountPage(driver);
    }

}
