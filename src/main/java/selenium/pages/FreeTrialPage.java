package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.data.UserData;
import selenium.management.BasePage;


public class FreeTrialPage extends BasePage{

    private UserData userData;

    @FindBy (name = "email")
    WebElement email;
    @FindBy (className = "icon-arrow-r")
    WebElement submit;

    public void fillUpEmail(UserData userData){
        email.sendKeys(userData.getEmail());
    }
    public void submitEmail(){
        submit.click();
    }

}

