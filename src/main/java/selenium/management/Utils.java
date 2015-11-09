package selenium.management;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.math.BigInteger;
import java.security.SecureRandom;


public class Utils {

    //private static WebDriver driver = BasePage.driver;
    public static WebDriver driver = BasePage.driver;

    /* public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(emailGenerator());
        }
    }*/

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


    public static boolean waiter(WebElement element){
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
