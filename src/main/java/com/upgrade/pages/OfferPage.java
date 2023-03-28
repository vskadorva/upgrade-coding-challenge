package com.upgrade.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OfferPage extends BasePage {
    public OfferPage(WebDriver driver) {
        super(driver);
        waitForWebElemetToBePresent(loanAmount);
    }

    @FindBy(css = "[data-auto='userLoanAmount']")
    private WebElement loanAmount;

    @FindBy(css = "[data-auto='defaultMonthlyPayment']")
    private WebElement monthlyPayment;

    @FindBy(css = "[data-auto='defaultLoanTerm']")
    private WebElement term;

    @FindBy(css = "[data-auto='defaultLoanInterestRate']")
    private WebElement interestRate;

    @FindBy(css = "[data-auto='defaultMoreInfoAPR']")
    private WebElement aprRate;

    @FindBy(css = "label.header-nav__toggle")
    private WebElement hamburgerMenu;

    @FindBy(linkText = "Sign Out")
    private WebElement signOut;

    public String getLoanAmount() {
        return loanAmount.getText();
    }

    public String getMonthlyPayment() {
        return monthlyPayment.getText();
    }

    public String getTerm() {
        return term.getText();
    }

    public String getInterestRate() {
        return interestRate.getText();
    }

    public String getAPRRate() {
        return aprRate.getText();
    }

    public SignOutPage signOut() {
        click(hamburgerMenu);
        waitForWebElemetToBePresent(signOut);
        click(signOut);
        return new SignOutPage(driver);
    }

}
