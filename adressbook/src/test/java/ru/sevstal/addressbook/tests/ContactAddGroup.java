package ru.sevstal.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sevstal.addressbook.model.ContactListData;
import ru.sevstal.addressbook.model.Contacts;
import ru.sevstal.addressbook.model.GroupData;
import ru.sevstal.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
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
    public void testAddingContactToGroup() {
        app.goTo().homePage();
        Contacts contactsBefore = app.db().contacts();
        Groups groupList = app.db().groups();
        ContactListData selectedContact = contactsBefore.iterator().next();
        GroupData toGroup = groupList.iterator().next();
        app.contact().addToGroup(selectedContact, toGroup);
        Contacts contactsAfter = app.db().contacts();
        assertThat(contactsAfter.iterator()
                .next().getGroups(),
                equalTo(contactsBefore
                .iterator().next()
                .getGroups()
                .withAdded(toGroup)));
        verifyContactListInUi();


        }
}
