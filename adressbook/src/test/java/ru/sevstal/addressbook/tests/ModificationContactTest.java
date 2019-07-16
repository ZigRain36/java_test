package ru.sevstal.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sevstal.addressbook.model.ContactListData;
import ru.sevstal.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ModificationContactTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() ==0) {
            app.goTo().homePage();
            app.contact().createContact(new ContactListData()
                    .withFirstName("Artem")
                    .withLastname("Zorin"), true);
        }
    }

    @Test
    public void testModificationContact() {
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        ContactListData modifiedContact = before.iterator().next();
        ContactListData contact = new ContactListData()
                .withId(modifiedContact.getId())
                .withFirstName("Artem")
                .withLastname("Zorin")
                .withNickName("Zigrain36");
        app.contact().modify(contact);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
        app.contact().logout();
    }
}
