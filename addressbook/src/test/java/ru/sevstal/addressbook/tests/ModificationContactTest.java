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
        if (! app.contact().isThereAContact()) {
            app.contact().createContact(new ContactListData()
                    .withFirstName("Artem")
                    .withLastname("Zorin"));
        }
    }

    @Test (enabled = false)
    public void testModificationContact() {
        app.contact().HomePage();
        Contacts before = app.contact().all();
//        app.contact().selectContact(before.size() - 1);
        ContactListData modifiedContact = before.iterator().next();
        ContactListData contact = new ContactListData()
                .withId(modifiedContact.getId())
                .withFirstName("Artem")
                .withLastname("Zorin")
                .withNickName("Zigrain36");
        app.contact().selectContact(contact.getId());
        app.contact().editContact(contact.getId());
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
        app.contact().logout();
    }
}
