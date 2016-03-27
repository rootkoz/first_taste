package addressbook.appmanager;

import addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
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
    }

    public void modify(GroupData groupData) {
        selectGroupById(groupData.getId());
        editSelectedGroup();
        editGroup(groupData);
        submitGroupUpdate();
    }


    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroup();
    }


    public boolean groupExists() {
        return isElementPresent(By.name("selected[]"));
    }

    public Set<GroupData> all() {
        Set<GroupData> groups = new HashSet<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            GroupData group = new GroupData().withName(name).withId(id);
            groups.add(group);
        }
        return groups;
    }


}
