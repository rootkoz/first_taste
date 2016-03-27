package addressbook.appmanager;

import addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
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

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
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

    public void modify(int index, GroupData groupData) {
        selectGroup(index);
        editSelectedGroup();
        editGroup(groupData);
        submitGroupUpdate();
    }

    public void delete(int index) {
        selectGroup(index);
        deleteSelectedGroup();
    }

    public boolean groupExists() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<GroupData>();
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
