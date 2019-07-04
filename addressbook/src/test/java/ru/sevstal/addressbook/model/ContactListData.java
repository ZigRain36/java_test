package ru.sevstal.addressbook.model;

import java.util.Objects;

public class ContactListData {
    private int id = Integer.MAX_VALUE;
    private String firstname;
    private String lastname;
    private String nickname;

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactListData that = (ContactListData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }

    @Override
    public String toString() {
        return "ContactListData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }


    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public ContactListData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactListData withFirstName(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactListData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactListData withNickName(String nickname) {
        this.nickname = nickname;
        return this;
    }


}
