package ru.sevstal.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.sevstal.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {
    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHepler().gotoGroupPage();
        int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
//        wd.findElement(By.linkText("Logout")).click();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before + 1);
    }
}

