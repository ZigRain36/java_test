package ru.sevstal.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sevstal.addressbook.model.ContactListData;
import ru.sevstal.addressbook.model.Contacts;
import ru.sevstal.addressbook.model.GroupData;
import ru.sevstal.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroupTest extends TestBase {

    private Contacts contacts;
    private Groups groups;

    @BeforeMethod
    public void preconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            File photo = new File("src/test/resources/tovChe.jpg");
            app.contact().create(new ContactListData()
                    .withFirstName("Artem")
                    .withLastname("Zorin"), true);
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("group name")
                    .withHeader("header")
                    .withFooter("footer"));
        }
        ContactListData contact = app.db().contacts().iterator().next();
        if (contact.getGroups().size() == 0) {
            app.goTo().homePage();
            app.contact().addToGroup(contact.inGroup(app.db().groups().iterator().next()));
        }
    }

    @Test
    public void testPersonDeleteGroup() {
        contacts = app.db().contacts();
        groups = app.db().groups();
        ContactListData contactDel = contacts.iterator().next();
        GroupData groupDel = groups.iterator().next();

        app.goTo().homePage();

        app.contact().contactGroupPage(contactDel);
        app.contact().delFromGroup(contactDel);

        app.db().personNextQuery(contactDel);
        app.db().groupsNextQuery(groupDel);

        assertThat(contactDel.getGroups(), not(groupDel));
        assertThat(groupDel.getContacts(), not(contactDel));






    }
}