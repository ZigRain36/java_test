package ru.sevstal.addressbook.tests;

import org.testng.annotations.Test;
import ru.sevstal.addressbook.model.ContactListData;

public class DeleteContactTest extends TestBase {

    @Test
    public void testContactDelete() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactListData("Artem", "Zorin", "ZigRain36", "Sevstal", "Voronezh", "9204181287", "artem-zorin@bk.ru", "5", "November", "1993", "test1"), true);
        }
        app.getContactHelper().selectFirstContact();
        app.getContactHelper().deleteContact();
        app.wd.switchTo().alert().accept();
        app.getContactHelper().logout();


    }
}
