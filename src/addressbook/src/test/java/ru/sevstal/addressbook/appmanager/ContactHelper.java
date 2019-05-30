package ru.sevstal.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.sevstal.addressbook.appmanager.HelperBase;
import ru.sevstal.addressbook.model.ContactListData;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }
    public void contactFormFill(ContactListData contactListData) {
        wd.findElement(By.name("firstname")).sendKeys(contactListData.getFirstname());
        wd.findElement(By.name("lastname")).sendKeys(contactListData.getLastname());
        wd.findElement(By.name("nickname")).sendKeys(contactListData.getNickname());
        wd.findElement(By.name("company")).sendKeys(contactListData.getCompany());
        wd.findElement(By.name("address")).sendKeys(contactListData.getAddress());
        wd.findElement(By.name("mobile")).sendKeys(contactListData.getMobile());
        wd.findElement(By.name("email")).sendKeys(contactListData.getEmail());
        new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactListData.getBday());
        wd.findElement(By.name("bmonth")).click();
        new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactListData.getBmouth());
        wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Birthday:'])[1]/following::option[45]")).click();
        wd.findElement(By.name("byear")).sendKeys(contactListData.getByear());
        wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]")).click();
        wd.findElement(By.xpath("//a[contains(text(),'home page')]")).click();
    }
    public void addNewContact() {
        click(By.linkText("add new"));
    }
}
