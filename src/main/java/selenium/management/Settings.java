package selenium.management;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.io.*;
import java.util.Properties;


public class Settings {

    public Settings() {
        try{
        loadSettings();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static final String SELENIUM_BASEURL = "selenium.baseUrl";
    private static final String SELENIUM_BROWSER = "selenium.browser";
    private static final String SELENIUM_PROPERTIES = "selenium.properties";

    private void loadSettings() throws Exception {
        properties = loadPropertiesFile();
        baseUrl = getProperty(SELENIUM_BASEURL);
        browser = BrowserType.browser(getProperty(SELENIUM_BROWSER));
    }

    private String baseUrl;
    private BrowserType browser;
    private Properties properties = new Properties();


    private Properties loadPropertiesFile() throws Exception {

        // get specified property file
        String filename = getProperty(SELENIUM_PROPERTIES);
        // it is not defined, use default
        if (filename == null) {
            filename = SELENIUM_PROPERTIES;
        }
        // try to load from classpath
        InputStream stream = getClass().getClassLoader().getResourceAsStream(filename);
        // no file in classpath, look on disk
        if (stream == null) {
            stream = new FileInputStream(new File(filename));
        }
        Properties result = new Properties();
        result.load(stream);
        return result;
    }

    private String getProperty(String name) {
        String result;
        if ((result = System.getProperty(name, null)) != null && result.length() > 0) {
            return result;
        } else if ((result = getPropertyFromPropertiesFile(name)) != null && result.length() > 0) {
            return result;
        }
        return result;
    }

    public WebDriver getDriver() throws Exception {
        return getDriver(browser);
    }

    private WebDriver getDriver(BrowserType browserType) throws IllegalArgumentException {
        switch (browserType) {
            case FIREFOX:
                return new FirefoxDriver();
            case IE:
                return new InternetExplorerDriver();
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "C:\\Windows\\System32\\chromedriver.exe");
                return new ChromeDriver();
            case OPERA:
                return new OperaDriver();
            default:
                throw new IllegalArgumentException("Cannot create driver for unknown browser type");
        }
    }

    private String getPropertyFromPropertiesFile(String name) {
        Object result = properties.get(name);
        if (result == null) {
            return null;
        } else {
            return result.toString();
        }
    }
    public String getBaseUrl() {
        return baseUrl;
    }
    public BrowserType getBrowser() {
        return browser;
    }
}
