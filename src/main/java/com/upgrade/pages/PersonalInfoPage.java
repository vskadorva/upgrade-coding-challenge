package com.upgrade.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PersonalInfoPage extends BasePage {

    public PersonalInfoPage(WebDriver driver) {
        super(driver);
        waitForWebElemetToBePresent(firstName);
    }

    @FindBy(name = "borrowerFirstName")
    private WebElement firstName;

    @FindBy(name = "borrowerLastName")
    private WebElement lastName;

    @FindBy(name = "borrowerStreet")
    private WebElement homeAddress;

    @FindBy(name = "borrowerCity")
    private WebElement city;

    @FindBy(name = "borrowerState")
    private WebElement state;

    @FindBy(name = "borrowerZipCode")
    private WebElement zipCode;

    @FindBy(name = "borrowerDateOfBirth")
    private WebElement dateOfBirth;

    @FindBy(css = "[data-auto='continuePersonalInfo']")
    private WebElement continueButton;

    @FindBy(css = "[class='geosuggest__suggests'] > li:first-of-type")
    private WebElement addressList;


    public void enterFirstName(String firstNameValue) {
        inputValue(firstName, firstNameValue);
    }

    public void enterLastName(String lastNameValue) {
        inputValue(lastName, lastNameValue);
    }

    public void enterHomeAddress(String homeAddressValue) {
        inputValue(homeAddress, homeAddressValue);
    }

    public void autoEnterHomeAddress(String civilNumber) {
        inputValue(homeAddress, civilNumber);
        pause(1);
        click(addressList);
    }

    public void eneterDateOfBirth(String dateOfBirthValue) {
        inputValue(dateOfBirth, dateOfBirthValue);
    }

    public PersonalIncomePage continueWithPersonalIncome() {
        click(continueButton);
        return new PersonalIncomePage(driver);
    }
}
