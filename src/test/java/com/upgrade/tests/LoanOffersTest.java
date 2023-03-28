package com.upgrade.tests;

import com.upgrade.pages.NonDMFunnelPage;
import com.upgrade.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class LoanOffersTest extends AbstractTest {


    NonDMFunnelPage nonDMFunnelPage = new NonDMFunnelPage(getDriver());
    PersonalInfoPage infoPage;
    PersonalIncomePage incomePage;
    CreateAccountPage createAccountPage;
    OfferPage offerPage;
    SignOutPage signOutPage;
    LoginPage loginPage;
    HashMap<String, String> map = new HashMap<>();

    private static final String url = "https://www.credify.tech/phone/nonDMFunnel";
    private final String loanAmount = "2000";
    private final String loanPurpose = "Business";
    private final String firstName = "TestFirstName";
    private final String lastName = "TestLastName";
    private final String civilNumber = "351";
    private final String dateOfBirth = "02/02/1990";
    private final String annualIncome = "150000";
    private final String additionalIncome = "10000";
    private final String email = "candidate" + getRandomNumber(100, 1000) + "@upgrade-challenge.com";
    private final String password = "Test1234";
    private final String loginUrl = "https://www.credify.tech/portal/login";

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @Test(priority = 1)
    public void validateCheckYourRate() {
        System.out.println("Running validation of validateCheckYourRate()");
        nonDMFunnelPage.navigateToNonDMFunnelPage(url);
        nonDMFunnelPage.enterLoanAmount(loanAmount);
        nonDMFunnelPage.selectLoanPurposeFromDropDown(loanPurpose);
        infoPage = nonDMFunnelPage.submitCheckingLoanRate();
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(loanPurpose.toUpperCase()));
        Assert.assertTrue(currentUrl.contains(loanAmount));
    }

    @Test(priority = 2)
    public void validateBasicContactData() {
        System.out.println("Running validation of validateBasicContactData()");
        infoPage.enterFirstName(firstName);
        infoPage.enterLastName(lastName);
        infoPage.autoEnterHomeAddress(civilNumber);
        infoPage.eneterDateOfBirth(dateOfBirth);
        incomePage = infoPage.continueWithPersonalIncome();
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("step=income"));
    }

    @Test(priority = 3)
    public void validateIncomeData() {
        System.out.println("Running validation of validateIncomeData()");
        incomePage.enterIndividualIncome(annualIncome);
        incomePage.enterAdditionalIncome(additionalIncome);
        createAccountPage = incomePage.continueWithCreateAccount();
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("step=login"));

    }

    @Test(priority = 4)
    public void validateCreateAccount() {
        System.out.println("Running validation of validateCreateAccount()");
        createAccountPage.enterUserEmail(email);
        createAccountPage.enterUserPassword(password);
        createAccountPage.acceptTermsOfUse();
        offerPage = createAccountPage.submitCheckYourRate();
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/offer-page"));
    }

    @Test(priority = 5)
    public void validateOffer() {
        System.out.println("Running validation of validateOffer()");
        /*
        Store values in map
         */
        map.put("offerLoanAmount", offerPage.getLoanAmount());
        map.put("offerMonthlyPayment", offerPage.getMonthlyPayment());
        map.put("offerTerm", offerPage.getTerm());
        map.put("offerInterestRate", offerPage.getInterestRate());
        map.put("offerAPRRate", offerPage.getAPRRate());

        signOutPage = offerPage.signOut();
        Assert.assertTrue(signOutPage.getSignOutText().contains("You've been successfully logged out."));
    }

    @Test(priority = 6)
    public void validateOfferInMyAccount() {
        System.out.println("Running validation of validateOfferInMyAccount()");
        loginPage = signOutPage.navigateToLoginAfterSignOut(loginUrl);
        loginPage.enterUserName(email);
        loginPage.enterPassword(password);
        offerPage = loginPage.signIn();
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/offer-page"));

      /*
        Assert values from map
       */
        Assert.assertEquals(map.get("offerLoanAmount"), offerPage.getLoanAmount());
        Assert.assertEquals(map.get("offerMonthlyPayment"), offerPage.getMonthlyPayment());
        Assert.assertEquals(map.get("offerTerm"), offerPage.getTerm());
        Assert.assertEquals(map.get("offerInterestRate"), offerPage.getInterestRate());
        Assert.assertEquals(map.get("offerAPRRate"), offerPage.getAPRRate());

        offerPage.signOut();
    }

}
