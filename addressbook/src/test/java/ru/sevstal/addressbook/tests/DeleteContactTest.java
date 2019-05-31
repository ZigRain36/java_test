package ru.sevstal.addressbook.tests;

import org.testng.annotations.Test;

public class DeleteContactTest extends TestBase {

    @Test
    public void testContactDelete() {
        app.getContactHelper().selectFirstContact();
        app.getContactHelper().deleteContact();
        app.wd.switchTo().alert().accept();
        app.getContactHelper().logout();


    }
}
