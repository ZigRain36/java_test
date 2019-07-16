package ru.sevstal.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "addressbook")
public class ContactListData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstname")
    private String firstname;

    @Expose
    @Column(name = "lastname")
    private String lastname;

    @Expose
    @Transient
    private String nickname;

    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String home;

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobile;

    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String work;

    @Transient
    private String allPhones;

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email1;

    @Column(name = "email2")
    @Type(type = "text")
    private String email2;

    public Groups getGroups() {
        return new Groups(groups);
    }

    public void setGroups(Set<GroupData> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "ContactListData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", home='" + home + '\'' +
                ", mobile='" + mobile + '\'' +
                ", work='" + work + '\'' +
                ", groups=" + groups +
                '}';
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private  Set<GroupData> groups = new HashSet<GroupData>();

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

    @Column(name = "email3")
    @Type(type = "text")
    private String email3;

    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Transient
    private String allEmails;

    @Column(name = "photo")
    @Transient
    private String photo;


    public File getPhoto() {
        return new File (photo) ;
    }

    public ContactListData withPhoto(File photo) {
        this.photo = photo.getPath();
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
    public ContactListData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
    public ContactListData delGroup() {
        groups.remove(getGroups().iterator().next());
        return this;
    }

}
