package ru.sevstal.addressbook.tests;

import org.testng.annotations.*;
import ru.sevstal.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {
    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHepler().gotoGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
//        wd.findElement(By.linkText("Logout")).click();
    }
}

