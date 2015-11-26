package selenium.pages;

import org.openqa.selenium.WebElement;
import selenium.management.BasePage;
import org.openqa.selenium.support.FindBy;
import selenium.management.Utils;

import java.util.concurrent.TimeUnit;


public class MainPage extends BasePage {

    @FindBy(className = "nav_login")
    WebElement headLogin;
    @FindBy(className = "nav_pull")
    WebElement navPull;
    @FindBy(className = "login")
    WebElement sideLogin;

    public MainPage getMainPage(){
        driver.get(settings.getBaseUrl());
        return initPage(MainPage.class);
    }

    public void goToLoginPage(){
        //If browser window has narrow width, navigation buttons are under left-sided "pull menu" button
        if (!headLogin.isDisplayed()){
            navPull.click();
            Utils.eagerWaiter(sideLogin);
            sideLogin.click();
        }
        else headLogin.click();
    }

}
