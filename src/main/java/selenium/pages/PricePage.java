package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.data.UserData;
import selenium.management.BasePage;


public class PricePage extends BasePage {

    @FindBy (xpath = "//h2/../div/a[@id='start-free-trial-professional']")
    WebElement getFreePro;
    @FindBy (xpath = "/*//*[@id=\"email\"]")
    WebElement email;
    @FindBy (id = "start-project")
    WebElement submit;
    @FindBy (id = "ajaxIframeFeatures")
    WebElement iframe;

    public void startProTrial(){
        getFreePro.click();
    }
    public void fillUpEmail(UserData userData){
        driver.switchTo().frame(iframe);
        email.sendKeys(userData.getEmail());
    }
    public void submitEmail(){
        submit.click();
        driver.switchTo().defaultContent();
    }

}
