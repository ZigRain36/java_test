package ru.sevstal.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.sevstal.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {
    @Test
    public void testGroupDeletion() throws Exception {
        app.getNavigationHepler().gotoGroupPage();
        int before = app.getGroupHelper().getGroupCount();
        if (! app.getGroupHelper().isthereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        app.getGroupHelper().selectGroups();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before - 1);
    }
}
