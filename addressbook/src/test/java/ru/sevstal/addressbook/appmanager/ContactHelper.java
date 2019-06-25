package ru.sevstal.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.sevstal.addressbook.model.ContactListData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void contactFormFill(ContactListData contactListData, boolean creation) {
        type(By.name("firstname"), contactListData.getFirstname());
        type(By.name("lastname"), contactListData.getLastname());
        type(By.name("nickname"), contactListData.getNickname());

//        if (creation) {
//            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactListData.getGroup());
//        } else {
//            Assert.assertFalse(isElementPresent(By.name("new_group")));
//        }
        click(By.xpath("//input[contains(@type, 'submit')]"));
//
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void editFirstContact(int index) {
        wd.findElements(By.xpath("(//input[contains(@type, 'checkbox')])[1]")).get(index).click();
        click(By.xpath("(//img[contains(@alt, 'Edit')])[1]"));
    }


    public void selectContact(int index) {
        wd.findElements(By.xpath("//img[contains(@title, 'Edit')]")).get(index).click();
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

    public List<ContactListData> getContactList() {
        List<ContactListData> contacts = new ArrayList<ContactListData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String name = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactListData contact = new ContactListData(id, name, lastname, null);
            contacts.add(contact);

        }
        return contacts;
    }
}
