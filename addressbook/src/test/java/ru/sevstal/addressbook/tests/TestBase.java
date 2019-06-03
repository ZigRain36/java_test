package ru.sevstal.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.sevstal.addressbook.appmanager.ApplicationManager;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager(BrowserType.IE);
    @BeforeMethod
    public void setUp() throws Exception {
        app.init();
        ApplicationManager.options.addArguments("--no-sandbox");
        ApplicationManager.options.addArguments("test-type");
        ApplicationManager.options.addArguments("--incognito");
        ApplicationManager.options.addArguments("--js-flags=--expose-gc");
        ApplicationManager.options.addArguments("--enable-precise-memory-info");
        ApplicationManager.options.addArguments("--disable-popup-blocking");
        ApplicationManager.options.addArguments("--disable-default-apps");
        ApplicationManager.options.addArguments("--enable-automation");
        ApplicationManager.options.addArguments("test-type=browser");
        ApplicationManager.options.addArguments("--window-size=1300,900");
        ApplicationManager.options.addArguments("disable-infobars");
        ApplicationManager.options.addArguments("disable-extensions");
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }
}
