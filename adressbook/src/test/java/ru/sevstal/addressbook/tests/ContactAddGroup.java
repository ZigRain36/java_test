package ru.sevstal.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sevstal.addressbook.model.ContactListData;
import ru.sevstal.addressbook.model.Contacts;
import ru.sevstal.addressbook.model.GroupData;
import ru.sevstal.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.*;

public class ContactAddGroup extends TestBase {

        @BeforeMethod
        public void preconditions() {
            if (app.db().contacts().size() == 0) {
                app.goTo().homePage();
                app.contact().addNewContact();
                app.contact().createContact(new ContactListData()
                        .withFirstName("Artem")
                        .withLastname("Zorin"), true);
            }
            if (app.db().groups().size() == 0) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("group name").withHeader("header").withFooter("footer"));
            }
        }

        @Test
        public void testContactAddToGroup() {

            Contacts contactBefore = app.db().contacts();
            Groups groupsBefore = app.db().groups();
            ContactListData contactAdd = contactBefore.iterator().next();
            GroupData groupsToAdd = groupsBefore.iterator().next();
            app.goTo().homePage();
            app.contact().addToGroup(contactAdd.inGroup(groupsToAdd));
            app.db().personNextQuery(contactAdd);
            app.db().groupsNextQuery(groupsToAdd);
            assertThat(contactAdd.getGroups(), hasItem(groupsToAdd));
            assertThat(groupsToAdd.getContacts(), hasItem(contactAdd));


        }
}
