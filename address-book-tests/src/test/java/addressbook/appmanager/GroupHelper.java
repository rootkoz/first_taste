package addressbook.appmanager;

import addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/***
 * by rootkoz
 * >(((*>
 */


public class GroupHelper {
    private FirefoxDriver wd;

    public GroupHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void fillGroupForm(GroupData groupData) {
        wd.findElement(By.name("group_name")).click();
        wd.findElement(By.name("group_name")).clear();
        wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select//option[1]")).isSelected()) {
            wd.findElement(By.xpath("//div[@id='content']/form/select//option[1]")).click();
        }
        wd.findElement(By.name("group_header")).click();
        wd.findElement(By.name("group_header")).clear();
        wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
        wd.findElement(By.name("group_footer")).click();
        wd.findElement(By.name("group_footer")).clear();
        wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    public void submitGroupCreation() {
        wd.findElement(By.name("submit")).click();
    }

    public void createNewGroup() {
        wd.findElement(By.name("new")).click();
    }

    public void groupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    public void deleteSelectedGroup() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[5]")).click();
    }

    public void selectGroup() {
        wd.findElement(By.name("selected[]")).click();
    }
    public void editGroup(String header, String footer, String groupname) {
        wd.findElement(By.name("edit")).click();
        wd.findElement(By.name("group_header")).click();
        wd.findElement(By.name("group_header")).clear();
        wd.findElement(By.name("group_header")).sendKeys(header);
        wd.findElement(By.name("group_footer")).click();
        wd.findElement(By.name("group_footer")).clear();
        wd.findElement(By.name("group_footer")).sendKeys(footer);
        wd.findElement(By.name("group_name")).click();
        wd.findElement(By.name("group_name")).clear();
        wd.findElement(By.name("group_name")).sendKeys(groupname);
        wd.findElement(By.name("update")).click();
    }
}
