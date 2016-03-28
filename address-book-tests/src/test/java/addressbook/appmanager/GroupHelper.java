package addressbook.appmanager;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/***
 * by rootkoz
 * >(((*>
 */


public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void submitGroupUpdate() {
        click(By.name("update"));
    }

    public void createNewGroupPage() {
        click(By.name("new"));
    }

    public void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void editSelectedGroup() {
        click(By.name("edit"));
    }

    public void deleteSelectedGroup() {
        click(By.name("delete"));
    }

    public void editGroup(GroupData groupData) {
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
        type(By.name("group_name"), groupData.getName());

    }

    public void create(GroupData group) {
        createNewGroupPage();
        editGroup(group);
        submitGroupCreation();
        groupsCache = null;
    }

    public void modify(GroupData groupData) {
        selectGroupById(groupData.getId());
        editSelectedGroup();
        editGroup(groupData);
        submitGroupUpdate();
        groupsCache = null;
    }


    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroup();
        groupsCache = null;
    }


    public boolean groupExists() {
        return isElementPresent(By.name("selected[]"));
    }

    public Groups groupsCache = null;

    public Groups all() {
        if (groupsCache != null){
            return new Groups(groupsCache);
        }

        groupsCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupsCache.add(new GroupData().withName(name).withId(id));
        }
        return new Groups(groupsCache);
    }


    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }
}
