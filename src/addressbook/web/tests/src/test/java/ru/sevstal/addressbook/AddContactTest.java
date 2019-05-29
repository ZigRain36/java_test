package ru.sevstal.addressbook;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


public class AddContactTest {
    private WebDriver wd;
    static FirefoxOptions options = new FirefoxOptions();

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "C:\\Books\\geckodriver.exe");
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        logon("user", "pass");
    }

    @Test
    public void testAddContact() throws Exception {
        addNewContact();
        fiilContactForm(new ContactListData("Artem", "Zorin", "ZigRain36", "Sevstal", "Voronezh", "9204181287", "artem-zorin@bk.ru", "5", "November", "1993"));
        logout();
    }

    private void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }

    private void fiilContactForm(ContactListData contactListData) {
        wd.findElement(By.name("firstname")).sendKeys(contactListData.getFirstname());
        wd.findElement(By.name("lastname")).clear();
        wd.findElement(By.name("lastname")).sendKeys(contactListData.getLastname());
        wd.findElement(By.name("nickname")).clear();
        wd.findElement(By.name("nickname")).sendKeys(contactListData.getNickname());
        wd.findElement(By.name("company")).clear();
        wd.findElement(By.name("company")).sendKeys(contactListData.getCompany());
        wd.findElement(By.name("address")).clear();
        wd.findElement(By.name("address")).sendKeys(contactListData.getAddress());
        wd.findElement(By.name("mobile")).clear();
        wd.findElement(By.name("mobile")).sendKeys(contactListData.getMobile());
        wd.findElement(By.name("email")).clear();
        wd.findElement(By.name("email")).sendKeys(contactListData.getEmail());
        new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactListData.getBday());
        wd.findElement(By.name("bmonth")).click();
        new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactListData.getBmouth());
        wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Birthday:'])[1]/following::option[45]")).click();
        wd.findElement(By.name("byear")).clear();
        wd.findElement(By.name("byear")).sendKeys(contactListData.getByear());
        wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]")).click();
//        wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
        wd.findElement(By.xpath("//a[contains(text(),'home page')]")).click();

    }

    private void addNewContact() {
        wd.findElement(By.linkText("add new")).click();
    }

    private void logon(String login, String password) {
        wd.findElement(By.name(login)).sendKeys("admin");
        wd.findElement(By.name(password)).sendKeys("secret");
        wd.findElement(By.id("LoginForm")).submit();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        wd.quit();

    }

    private boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }


}
