package ru.sevstal.addressbook.model;

import java.util.Objects;

public class ContactListData {
    private int id;
    private final String firstname;
    private final String lastname;
    private final String nickname;



    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactListData that = (ContactListData) o;
        return Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname);
    }

    public int getId() {
        return id;
    }

    public ContactListData(int id, String firstname, String lastname, String nickname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = nickname;

    }



    public ContactListData(String firstname, String lastname, String nickname, boolean b) {
        this.id = Integer.MAX_VALUE;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = nickname;

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


    @Override
    public String toString() {
        return "ContactListData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                '}';
    }

}
