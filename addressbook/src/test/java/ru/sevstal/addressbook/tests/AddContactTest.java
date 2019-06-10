package ru.sevstal.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.sevstal.addressbook.model.ContactListData;

public class AddContactTest extends TestBase {
    @Test
    public void testAddContact() throws Exception {
        int before = app.getGroupHelper().getGroupCount();
        app.getContactHelper().addNewContact();
        app.getContactHelper().contactFormFill(new ContactListData("Artem", "Zorin", "ZigRain36", "Sevstal", "Voronezh", "9204181287", "artem-zorin@bk.ru", "5", "November", "1993", "test1"), true);
        app.getContactHelper().gotoHomePage();
        app.getContactHelper().logout();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before + 1);
    }
}
