package ru.sevstal.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sevstal.addressbook.model.ContactListData;
import ru.sevstal.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class DeleteContactTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (!app.contact().isThereAContact()) {
            app.contact().createContact(new ContactListData()
                    .withFirstName("Artem")
                    .withLastname("Zorin"));
        }
    }

    @Test
    public void testContactDelete() {
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        ContactListData deletedContacts = before.iterator().next();
        app.contact().delete(deletedContacts);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withOut(deletedContacts)));

    }
}
