package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.management.BasePage;


public class LoginPage extends BasePage{

    @FindBy(className = "w3link")
    WebElement singUp;

    public void clickSignUp(){
        singUp.click();
    }

}
