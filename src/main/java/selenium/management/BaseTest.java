package selenium.management;

import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;


public abstract class BaseTest {

    private static Settings settings = new Settings();

    @BeforeSuite(alwaysRun = true)
    public static void beforeSuite() throws Exception {
        BasePage.driver = settings.getDriver();
        BasePage.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        BasePage.settings = settings;
        BasePage.driver.get(settings.getBaseUrl());
    }

    @AfterSuite(alwaysRun = true)
    public static void afterClass() {
        if (BasePage.driver.getWindowHandles().size() > 0) {
            BasePage.driver.close();
        }
        else {
            BasePage.driver.quit();
        }
    }
}
