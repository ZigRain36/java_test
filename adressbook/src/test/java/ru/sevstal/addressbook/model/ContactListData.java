package ru.sevstal.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

public class ContactListData {
    @XStreamOmitField
    private int id = Integer.MAX_VALUE;
    @Expose
    private String firstname;
    @Expose
    private String lastname;
    @Expose
    private String nickname;
    @Expose
    private String home;
    @Expose
    private String mobile;
    @Expose
    private String work;
    private String allPhones;
    @Expose
    private String email1;
    private String email2;
    private String email3;
    @Expose
    private String address;
    private String allEmails;
    private File photo;


    public File getPhoto() {
        return photo;
    }

    public ContactListData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }


    public String getAllPhones() {
        return allPhones;
    }

    public ContactListData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactListData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactListData withAddress(String address) {
        this.address = address;
        return this;
    }

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

    public String getHomePhone() {
        return home;
    }

    public String getMobilePhone() {
        return mobile;
    }

    public String getWorkPhone() {
        return work;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getAddress() {
        return address;
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

    public ContactListData withHomePhone(String home) {
        this.home = home;
        return this;
    }

    public ContactListData withMobilePhone(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactListData withWorkPhone(String work) {
        this.work = work;
        return this;
    }

    public ContactListData withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }

    public ContactListData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactListData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

}
