package ru.sevstal.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sevstal.addressbook.model.ContactListData;
import ru.sevstal.addressbook.model.Contacts;
import ru.sevstal.addressbook.model.GroupData;
import ru.sevstal.addressbook.model.Groups;

import java.io.File;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroupTest extends TestBase {

    private Contacts contacts;
    private Groups groups;

    @BeforeMethod
    public void preconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactListData()
                    .withFirstName("A/")
                    .withLastname("Korsakova")
                    .withAddress("Minsk")
                    .withHomePhone("1111111111")
                    .withMobilePhone("22222222222")
                    .withWorkPhone("33333333333")
                    .withEmail1("email@email.com"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
        if (app.db().contacts().iterator().next().getGroups().size() == app.db().groups().size()) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
    }

    @Test
    public void testRemovingContactFromGroup() {
        app.goTo().homePage();
        Contacts contactsBefore = app.db().contacts();
        Groups groupList = app.db().groups();
        ContactListData selectedContact = contactsBefore.iterator().next();
        GroupData fromGroup = groupList.iterator().next();
        app.contact().delFromGroup(selectedContact, fromGroup);
        Contacts contactsAfter = app.db().contacts();
        assertThat(contactsAfter.iterator().next().getGroups(), equalTo(contactsBefore.iterator().next().getGroups().withOut(fromGroup)));
        app.goTo().homePage();
        verifyContactListInUi();
    }

}