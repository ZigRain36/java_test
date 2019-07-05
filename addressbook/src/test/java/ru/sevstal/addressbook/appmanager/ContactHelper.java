package ru.sevstal.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.sevstal.addressbook.model.ContactListData;
import ru.sevstal.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void createContact(ContactListData contactListData) {
        type(By.name("firstname"), contactListData.getFirstname());
        type(By.name("lastname"), contactListData.getLastname());
        type(By.name("nickname"), contactListData.getNickname());
        click(By.xpath("//input[contains(@type, 'submit')]"));
    }
    public void create(ContactListData contact) {
        addNewContact();
        createContact(contact);
        HomePage();
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void editContact(int id) {
        wd.findElement(By.xpath(String.format("//input[@value = '%s']/../../td[8]/a", id))).click();
    }

    public void modify(ContactListData contact) {
        editContact(contact.getId());
        createContact(contact);
    }

    private void updateContact() {
        click(By.xpath("//input[contains(@name, 'update')][2]"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deletedSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void delete(ContactListData contact) {
        selectContactById(contact.getId());
        deletedSelectedContacts();
        wd.switchTo().alert().accept();
        wd.findElement(By.cssSelector("div.msgbox"));
    }

    public void HomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name = 'entry']"));
        for (WebElement element : elements) {
            String name = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactListData().withId(id).withFirstName(name).withLastname(lastname));

        }
        return contacts;
    }
}
