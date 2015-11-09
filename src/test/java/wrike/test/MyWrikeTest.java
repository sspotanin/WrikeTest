package wrike.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.data.*;
import selenium.management.BaseTest;
import selenium.pages.*;

import static selenium.management.BasePage.initPage;
import static selenium.data.UserDataBuilder.userData;



public class MyWrikeTest extends BaseTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private ResendPage resendPage;
    private PricePage pricePage;
    private FreeTrialPage freeTrialPage;
    private UserData userData;
    private boolean result;

    @BeforeMethod(alwaysRun = true)
    public void getPages(){

        mainPage = initPage(MainPage.class);
        loginPage = initPage(LoginPage.class);
        resendPage = initPage(ResendPage.class);
        pricePage = initPage(PricePage.class);
        freeTrialPage = initPage(FreeTrialPage.class);
    }

    @Test(priority = 0)
    public void checkFreeRegistration(){
        mainPage.getMainPage(); // 1.open url: wrike.com
        mainPage.goToLoginPage(); // 2.click "Login" button
        loginPage.clickSignUp(); // 3.click "sign Up"
        userData = userData();
        freeTrialPage.fillUpEmail(userData); // 4.fill it up email address(random generated)
        freeTrialPage.submitEmail(); // 5.click "Get started for free" button
        result = resendPage.registrationAssert(); // 6.check at the loaded page, that you have success confirmation of registration
        resendPage.clickResend(); // 7.click resend button
    }

    @Test(priority = 1)
    public void checkFreeProTrial(){
        if (!result) resendPage.getResendPage(); // in case of first test's failure
        resendPage.goToPricePage(); // 8.open "Pricing" link
        pricePage.startProTrial(); // 9.click "Get started for free" for professional plan
        userData = userData();
        pricePage.fillUpEmail(userData); // 10.In appeared window fill it up another random genearted email
        pricePage.submitEmail(); // 11.click "Create my Wrike account" button
        resendPage.registrationAssert(); // 12.check at the loaded page, that you have success confirmation of registration
        resendPage.clickResend(); // 13.click resend button
    }

}
