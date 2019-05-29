package ru.sevstal.addressbook;

public class ContactListData {
    private final String firstname;
    private final String lastname;
    private final String nickname;
    private final String company;
    private final String address;
    private final String mobile;
    private final String email;
    private final String bday;
    private final String bmouth;
    private final String byear;

    public ContactListData(String firstname, String lastname, String nickname, String company, String address, String mobile, String email, String bday, String bmouth, String byear) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.company = company;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.bday = bday;
        this.bmouth = bmouth;
        this.byear = byear;
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

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getBday() {
        return bday;
    }

    public String getBmouth() {
        return bmouth;
    }

    public String getByear() {
        return byear;
    }
}
