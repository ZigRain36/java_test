package ru.sevstal.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.sevstal.addressbook.appmanager.HelperBase;
import ru.sevstal.addressbook.model.ContactListData;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void contactFormFill(ContactListData contactListData, boolean creation) {
        type(By.name("firstname"), contactListData.getFirstname());
        type(By.name("lastname"), contactListData.getLastname());
        type(By.name("nickname"), contactListData.getNickname());
        type(By.name("company"), contactListData.getCompany());
        type(By.name("address"), contactListData.getAddress());
        type(By.name("mobile"), contactListData.getMobile());
        type(By.name("email"), contactListData.getEmail());
        new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactListData.getBday());
        click(By.name("bmonth"));
        new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactListData.getBmouth());
        click(By.xpath("//option[@value='November']"));
        type(By.name("byear"), contactListData.getByear());
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactListData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
        click(By.xpath("//input[contains(@type, 'submit')]"));
        }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void editFirstContact() {
        click(By.xpath("(//input[contains(@type, 'checkbox')])[1]"));
        click(By.xpath("(//img[contains(@alt, 'Edit')])[1]"));
    }


    public void selectFirstContact() {
        click(By.name("selected[]"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void createContact(ContactListData contact, boolean b) {
        addNewContact();
        contactFormFill(contact, b);
        gotoHomePage();
    }

    public void gotoHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("Home"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
}
