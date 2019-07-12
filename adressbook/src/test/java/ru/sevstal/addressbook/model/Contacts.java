package ru.sevstal.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactListData> {
    private Set<ContactListData> delegate;

    public Contacts(Contacts contacts) {
        this.delegate = new HashSet<ContactListData>(contacts.delegate);
    }

    public Contacts() {
        this.delegate = new HashSet<ContactListData>();
    }

    @Override
    protected Set<ContactListData> delegate() {
        return delegate;
    }

    public Contacts withAdded(ContactListData contact) {
        Contacts contacts = new Contacts(this);
        contacts.add(contact);
        return contacts;
    }

    public Contacts withOut(ContactListData contact) {
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }

}
