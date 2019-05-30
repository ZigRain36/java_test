package ru.sevstal.addressbook.tests;

import org.testng.annotations.Test;
import ru.sevstal.addressbook.model.ContactListData;

public class ModificationContactTest extends TestBase {

    @Test
    public void testModificationContact() {
        app.getContactHelper().editFirstContact();
        app.getContactHelper().contactFormFill(new ContactListData("Artem", "Zorin", "ZigRain36", "Sevstal", "Voronezh", "9204181287", "artem-zorin@bk.ru", "5", "November", "1993"));
        app.getContactHelper().logout();
    }
}
