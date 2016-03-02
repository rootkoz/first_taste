package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.annotations.Test;


public class GroupCreationTest extends TestBase {

    @Test
    public void testCreateGroup() {
        groupHelper.groupPage();
        groupHelper.createNewGroup();
        groupHelper.fillGroupForm(new GroupData("G5", "G5-header", "G5-footer"));
        groupHelper.submitGroupCreation();
        groupHelper.groupPage();
    }

}
