package ru.sevstal.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.sevstal.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {
    @Test
    public void testGroupDeletion() throws Exception {
        app.getNavigationHepler().gotoGroupPage();
        if (! app.getGroupHelper().isthereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroups(before.size() - 1);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
            Assert.assertEquals(before, after);

    }
}
