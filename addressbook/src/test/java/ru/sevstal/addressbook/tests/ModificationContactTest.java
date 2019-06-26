package ru.sevstal.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sevstal.addressbook.model.ContactListData;

import java.util.Comparator;
import java.util.List;

public class ModificationContactTest extends TestBase {

    @Test
    public void testModificationContact() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactListData("Artem", "Zorin", "ZigRain36", true), true);
        }
        List<ContactListData> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContact(before.size() - 1);
        ContactListData contact = new ContactListData(before.get(before.size() - 1).getId(), "Artem", "Zorin", "ZigRain36");
        app.getContactHelper().contactFormFill(contact, true);
        List<ContactListData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactListData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
        app.getContactHelper().logout();
    }
}
