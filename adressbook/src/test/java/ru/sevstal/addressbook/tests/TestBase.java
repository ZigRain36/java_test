package ru.sevstal.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.sevstal.addressbook.appmanager.ApplicationManager;
import ru.sevstal.addressbook.model.GroupData;
import ru.sevstal.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    protected static final ApplicationManager
            app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite
    void setUp() throws Exception {
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

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }
    public void verifyGroupListUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupData()
                            .withId(g.getId())
                            .withName(g.getGroupName()))
                    .collect(Collectors.toSet())));
        }
    }
}
