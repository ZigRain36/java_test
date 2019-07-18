package ru.sevstal.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final Properties properties;
    private WebDriver wd;

    private String browser;
    private RegistrationHelper registrtationHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(
                String.format("src/test/resources/%s.properties", target))));
             }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if (registrtationHelper == null) {
            registrtationHelper =  new RegistrationHelper(this);
        }
        return registrtationHelper;
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equals(BrowserType.FIREFOX)) {
                System.setProperty("webdriver.gecko.driver", "C:\\Books\\geckodriver.exe");
                wd = new FirefoxDriver();
            } else if (browser.equals(BrowserType.CHROME)) {
                System.setProperty("webdriver.chrome.driver", "C:\\Books\\chromedriver.exe");
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.IE)) {
                System.setProperty("webdriver.ie.driver", "C:\\Books\\IEDriverServer.exe");
                DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
                ieCapabilities.setCapability(
                        InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                wd = new InternetExplorerDriver(ieCapabilities);
            }
            wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }

}
