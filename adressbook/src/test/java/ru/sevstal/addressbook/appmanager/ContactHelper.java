package ru.sevstal.addressbook.appmanager;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.sevstal.addressbook.model.ContactListData;
import ru.sevstal.addressbook.model.Contacts;

import java.util.List;


public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void createContact(ContactListData contactListData, boolean creation) {
        type(By.name("firstname"), contactListData.getFirstname());
        type(By.name("lastname"), contactListData.getLastname());
        type(By.name("nickname"), contactListData.getNickname());
        type(By.name("home"), contactListData.getHomePhone());
        type(By.name("mobile"), contactListData.getMobilePhone());
        type(By.name("work"), contactListData.getWorkPhone());
        type(By.name("email"), contactListData.getEmail1());
        type(By.name("address"), contactListData.getAddress());
//        attach(By.name("photo"), contactListData.getPhoto());


        if (creation) {
            if (contactListData.getGroups().size() != 0) {
                Assert.assertTrue(contactListData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group")))
                        .selectByVisibleText(contactListData
                                .getGroups().iterator().next().getGroupName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
        click(By.xpath("//input[contains(@type, 'submit')]"));
    }

    public void create(ContactListData contact,boolean creation) {
        addNewContact();
        createContact(contact, creation);
        returnToHomePage();
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void editContact(int id) {
        wd.findElement(By.xpath(String.format("//input[@value = '%s']/../../td[8]/a", id))).click();
    }

    public void modify(ContactListData contact) {
        editContact(contact.getId());
        createContact(contact, false);
    }
    public void select(ContactListData contact) {
        click(By.name("selected[]"));
    }

    public void returnToHomePage() {
        click(By.linkText("home"));
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

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name = 'entry']"));
        for (WebElement element : elements) {
            String name = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
            String address = element.findElement(By.xpath(".//td[4]")).getText();
            String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactListData().withId(id).withFirstName(name).withLastname(lastname)
                    .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));

        }
        return contacts;
    }

    public ContactListData infoFromEditForm(ContactListData contact) {
        initContactModificationById(contact.getId());
        String name = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactListData().withId(contact.getId()).withFirstName(name).withLastname(lastname)
                .withEmail1(email1).withEmail2(email2).withEmail3(email3).withAddress(address)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }

    public void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();


//        wd.findElement(By.xpath(String.format("//input[value='%s']/../../td[8]/a", id))).click();
//        wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
//        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void approvedAddGroup(ContactListData contactAdd) {
        wd.findElement(By.tagName("h1")).getText().equals("Groups");
        Assert.assertTrue(isElementPresent(By.linkText("group page \""
                + contactAdd.getGroups().iterator().next().getGroupName() +"\"")));

    }

    public void approvedDelGroup(ContactListData deletedContact) {
        wd.findElement(By.tagName("h1")).getText().equals("Groups");
        Assert.assertTrue(isElementPresent(By.linkText("group page \""
                + deletedContact.getGroups().iterator().next().getGroupName() +"\"")));
    }


    public void addToGroup(ContactListData contactAdd) {
        Assert.assertEquals(contactAdd.getGroups().size(), 1);
        Select groupSelector = new Select(wd.findElement(By.name("to_group")));
        groupSelector.selectByVisibleText(contactAdd.getGroups().iterator().next().getGroupName());
        select(contactAdd);
        click(By.name("add"));
        approvedAddGroup(contactAdd);
    }

    public void delFromGroup(ContactListData contactDelete) {
        Assert.assertEquals(contactDelete.getGroups().size(), 1);
        selectContactById(contactDelete.getId());
        click(By.name("remove"));
        approvedDelGroup(contactDelete);
    }

    public void contactGroupPage(ContactListData contactDel) {

        Select select = new Select(wd.findElement(By.name("group")));
        select.selectByVisibleText(contactDel.getGroups().iterator().next().getGroupName());

    }
}
