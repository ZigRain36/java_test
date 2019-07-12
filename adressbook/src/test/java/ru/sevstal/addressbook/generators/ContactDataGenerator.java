package ru.sevstal.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sevstal.addressbook.model.ContactListData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target false")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (Exception ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactListData> contacts = generateContacts(count);
        if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsJson(List<ContactListData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private List<ContactListData> generateContacts(int count) {
        List<ContactListData> contacts = new ArrayList<ContactListData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactListData()
                    .withFirstName(String.format("Artem %s", i))
                    .withLastname(String.format("Zorin %s", i))
                    .withHomePhone(String.format("+7999 ", i))
                    .withMobilePhone(String.format("22-22 ", i))
                    .withWorkPhone(String.format("33 33 ", i))
                    .withEmail1(String.format("test@test.com", i))
                    .withAddress(String.format("Baker street, 221b")));
        }
        return contacts;
    }

}
