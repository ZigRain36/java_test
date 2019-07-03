package ru.sevstal.addressbook.tests;

import org.testng.annotations.Test;
import ru.sevstal.addressbook.model.ContactListData;
import ru.sevstal.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class AddContactTest extends TestBase {

    @Test
    public void testAddContact() throws Exception {
        app.contact().HomePage();
        Contacts before = app.contact().all();
        app.contact().addNewContact();
        ContactListData contact = new ContactListData().withFirstName("Artem").withLastname("Zorin");
        app.contact().create(contact);
        app.contact().HomePage();
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        assertThat(after.size(), equalTo(before.size() + 1));
    }

}
