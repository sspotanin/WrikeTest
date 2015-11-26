package wrike.test;

import selenium.management.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SimpleWrikeTest {

    private WebDriver driver;
    private WebElement webElement;

    @BeforeClass
    public void setUp() {
        driver = Utils.driver;
        // You need to put included 'chromedriver.exe' into c:/windows/system32 to avoid driver errors
    }

    @Test
    public void confirmRegistration(){

        //driver settings
        //driver.manage().window().maximize(); //uncomment for non-full size
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://www.wrike.com");// 1.open url: wrike.com
        webElement = driver.findElement(By.className("nav_login"));

        //If browser window has narrow width, navigation buttons are under 'side menu' button
        if (!webElement.isDisplayed()){ // 2.click "Login" button
            driver.findElement(By.className("nav_pull")).click();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(By.className("login")));
            driver.findElement(By.className("login")).click();
        } else webElement.click();
        driver.findElement(By.className("w3link")).click();// 3.click "sign Up"
        driver.findElement(By.name("email")).sendKeys(Utils.emailGenerator());// 4.fill it up email address(random generated)
        driver.findElement(By.className("icon-arrow-r")).click();// 5.click "Get started for free" button
        String text = driver.findElement(By.className("text")).getText();
        Assert.assertEquals(text, "Registration succeded");// 6.check at the loaded page, that you have success confirmation of registration
        driver.findElement(By.id("resendEmail")).click();// 7.click resend button
        webElement = driver.findElement(By.xpath("//nav/ul/li[4]/a"));

        //If browser window has narrow width, navigation buttons are under 'side menu' button
        if (!webElement.isDisplayed()) { // 8.open "Pricing" link
            driver.findElement(By.className("nav_pull")).click();
            Utils.waiterNavPull(webElement);
            webElement.click();
        } else webElement.click();

        //driver.get("https://www.wrike.com/pricing"); //TEMPORARY for debugging

        driver.findElement(By.xpath("//h2/../div/a[@id='start-free-trial-professional']")).click(); // 9.click "Get started for free" for professional plan
        driver.switchTo().frame(driver.findElement(By.id("ajaxIframeFeatures")));
        driver.findElement(By.xpath("/*//*[@id=\"email\"]")).sendKeys(Utils.emailGenerator()); // 10.In appeared window fill it up another random genearted email
        driver.findElement(By.id("start-project")).click();// 11.click "Create my Wrike account" button
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.switchTo().defaultContent();
        webElement = driver.findElement(By.xpath("//div[1]/div/div[@class='text']"));
        text = driver.findElement(By.xpath("//div[1]/div/div[@class='text']")).getText();
        Assert.assertEquals(text, "Registration succeded");// 12.check at the loaded page, that you have success confirmation of registration
        driver.findElement(By.xpath("//*[@id=\"resendEmail\"]")).click(); // 13.click resend button

    }


    @AfterClass
    public void shutDown() {
        // Close browser window and clean operative memory
        driver.close();
        driver.quit();
    }

}