package ru.sevstal.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sevstal.addressbook.model.ContactListData;

import java.util.List;

public class DeleteContactTest extends TestBase {

    @Test
    public void testContactDelete() {
        app.getContactHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactListData("Artem", "Zorin", "ZigRain36", true), true);
        }
        List<ContactListData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteContact();
        List<ContactListData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
        app.getContactHelper().logout();


    }
}
