package ru.sevstal.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.sevstal.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }
    public void returnToGroupPage() {

        click(By.linkText("group page"));
    }
    public void submitGroupCreation() {

        click(By.name("submit"));
    }
    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        wd.findElement(By.name("group_header")).clear();
        wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
        wd.findElement(By.name("group_footer")).clear();
        wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());

    }
    public void initGroupCreation() {
        click(By.name("new"));
    }
    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }
    public void selectGroups() {
        click(By.name("selected[]"));
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupMdification() {
        click(By.name("update"));
    }


}
