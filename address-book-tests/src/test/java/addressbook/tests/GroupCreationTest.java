package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.annotations.Test;


public class GroupCreationTest extends TestBase {

    @Test
    public void testCreateGroup() {
        groupPage();
        createNewGroup();
        fillGroupForm(new GroupData("G5", "G5-header", "G5-footer"));
        submitGroupCreation();
        groupPage();
    }

}
