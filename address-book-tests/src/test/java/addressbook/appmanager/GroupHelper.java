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


public class GroupHelper extends HelperBase{

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

    public void selectGroup() {

        click(By.name("selected[]"));
    }

    public void editSelectedGroup() {
        click(By.name("edit"));
    }

    public void deleteSelectedGroup() {
        click(By.name("delete"));
    }

    public void modifyGroup(GroupData groupData) {
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
        type(By.name("group_name"), groupData.getName());

    }

    public void createGroup(GroupData group) {
       createNewGroupPage();
       modifyGroup(group);
       submitGroupCreation();
    }

    public boolean groupExists() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<GroupData> getGroupList() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements){
            String name = element.getText();
            GroupData group = new GroupData(name, null, null);
            groups.add(group);
        }
        return groups;
    }
}
