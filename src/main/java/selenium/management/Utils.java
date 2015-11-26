package selenium.management;

import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.math.BigInteger;
import java.security.SecureRandom;


public class Utils {

    //private static WebDriver driver = BasePage.driver;
    public static WebDriver driver = BasePage.driver;




      public static String emailGenerator(){
        StringBuilder email = new StringBuilder();
        SecureRandom random = new SecureRandom();
        int length = (int)(Math.random()*100);
        String name = new BigInteger(length, random).toString(36);
        email.append(name);
        email.append("@");
        name = new BigInteger(length, random).toString(36);
        email.append(name);
        email.append(".com");
        return email.toString();
    }

    public static boolean eagerWaiter(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement.isDisplayed();
    }

    public static void waitPageLoad(int milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static boolean waiterNavPull(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        //It seems that the element stops being clickable for a while
        //so we have "Element is not clickable at point" exception without this part
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return element.isDisplayed();
    }

}
