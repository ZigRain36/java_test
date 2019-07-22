package ru.sevstal.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.sevstal.addressbook.model.ContactListData;
import ru.sevstal.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class AddContactTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(
                new File("src/test/resources/contacts.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ContactListData> contacts = gson.fromJson(json, new TypeToken<List<ContactListData>>() {
        }.getType());
        return contacts.stream().map((g -> new Object[]{g})).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testAddContact(ContactListData contact) throws Exception {
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        app.contact().addNewContact();
//        File photo = new File("src/test/resources/stru.png");
        app.contact().create(contact.inGroup(app.db().groups().iterator().next()));
        app.goTo().homePage();
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        assertThat(after.size(), equalTo(before.size() + 1));
    }
}
