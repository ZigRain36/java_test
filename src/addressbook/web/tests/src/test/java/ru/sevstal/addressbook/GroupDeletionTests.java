package ru.sevstal.addressbook;

import org.testng.annotations.*;


public class GroupDeletionTests extends TestBase {


    @Test
    public void testGroupDeletion() throws Exception {
        gotoGroupPage();
        selectGroups();
        deleteSelectedGroups();
        returnToGroupPage();
    }


}
