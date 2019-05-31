package ru.sevstal.addressbook.tests;

import org.testng.annotations.Test;
import ru.sevstal.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHepler().gotoGroupPage();
        app.getGroupHelper().selectGroups();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
        app.getGroupHelper().submitGroupMdification();
        app.getGroupHelper().returnToGroupPage();
    }
}
