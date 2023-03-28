package com.upgrade.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateAccountPage extends BasePage{

    public CreateAccountPage(WebDriver driver) {
        super(driver);
        waitForWebElemetToBePresent(email);
    }

    @FindBy(name = "username")
    private WebElement email;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(name = "agreements")
    private WebElement termOfUseCheckBox;

    @FindBy(css = "[data-auto='submitPersonalInfo']")
    private WebElement checkYourRateButton;

    public void enterUserEmail(String emailValue){
        inputValue(email, emailValue);
    }

    public void enterUserPassword(String passwordValue){
        inputValue(password, passwordValue);
    }

    public void acceptTermsOfUse(){
        selectTermOfUseCheckBox();
    }

    public OfferPage submitCheckYourRate(){
        click(checkYourRateButton);
        return new OfferPage(driver);
    }

    public CreateAccountPage selectTermOfUseCheckBox(){
        forceClick(termOfUseCheckBox);
        return this;
    }
}
