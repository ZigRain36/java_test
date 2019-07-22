package ru.sevstal.mantis.appmanager;

import org.openqa.selenium.By;

public class PasswordResetHelper extends HelperBase {

  private String user;
  private String username;

  public PasswordResetHelper(ApplicationManager app) {
    super(app);
  }


  public void goToManagePage(){
    wd.findElement(By.linkText("Manage")).click();
  }

  public void goToManageUsersPage(){
    wd.findElement(By.linkText("Manage Users")).click();
  }

  public void resetPassword(String user) {
    this.username = user;
    click(By.linkText(username));
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public String getUserName(){
    String username = wd.findElement(By.cssSelector("input[name='username']")).getAttribute("value");
    return username;
  }

  public String getMail(){
    String email = wd.findElement(By.cssSelector("input[name='email']")).getAttribute("value");
    return email;
  }

  public void changePasswordFinish(String resetPasswordLink, String newpassword){
    wd.get(resetPasswordLink);
    type(By.name("password"),newpassword);
    type(By.name("password_confirm"),newpassword);
    click(By.cssSelector("input[value='Update User']"));
  }

}