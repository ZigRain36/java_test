package ru.sevstal.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.sevstal.addressbook.model.ContactListData;

import java.util.Comparator;
import java.util.List;

public class AddContactTest extends TestBase {
    @Test
    public void testAddContact() throws Exception {
        app.getContactHelper().gotoHomePage();
        List<ContactListData> before = app.getContactHelper().getContactList();
        app.getContactHelper().addNewContact();
        ContactListData contact = new ContactListData("Artem", "Zorin", null, true);
        app.getContactHelper().contactFormFill(contact, true);
        app.getContactHelper().gotoHomePage();
        List<ContactListData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactListData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }
}
