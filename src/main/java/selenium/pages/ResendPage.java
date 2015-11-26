package selenium.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.management.BasePage;
import selenium.management.Utils;


public class ResendPage extends BasePage {

    @FindBy (xpath = "//div[1]/div/div[@class='text']")
    WebElement suceededText;
    @FindBy (id = "resendEmail")
    WebElement resend;
    @FindBy (xpath = "//nav/ul/li[4]/a") //side and head "Pricing" buttons have the same xpath locator
    WebElement pricing;
    @FindBy(className = "nav_pull")
    WebElement navPull;


    public boolean registrationAssert(){
        Utils.waitPageLoad(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("/resend"));
        return driver.getCurrentUrl().contains("/resend");
    }

    public void clickResend() {
        resend.click();
    }


    public void getResendPage(){
        driver.get("https://www.wrike.com/resend"); //we will actually go to the main page, but navigation elements are the same
    }

    public void goToPricePage() {
        if (!pricing.isDisplayed()) {
            navPull.click();
            Utils.eagerWaiter(pricing);
            pricing.click();
        } else pricing.click();
    }

}
