package ru.stqa.pft1.mantis.model;
import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mantis_user_table")

public class UserData {


  @Id
  @Column(name = "id")
  private int id;
  @Expose
  @Column (name = "username")
  private  String username;

  public String getEmail() {
    return email;
  }

  @Expose
  @Column (name = "email")
  private  String email;


  public String getUsername(){
    return username;
  }


  @Override
  public String toString() {
    return "UserData{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            '}';
  }
}