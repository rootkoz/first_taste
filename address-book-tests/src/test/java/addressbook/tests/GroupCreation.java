package addressbook.tests;
/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.GroupData;
import org.testng.annotations.Test;


public class GroupCreation extends TestBase {

    @Test
    public void testCreateGroup() {
        navigationHelper.groupPage();
        groupHelper.createNewGroup();
        groupHelper.fillGroupForm(new GroupData("G5", "G5-header", "G5-footer"));
        groupHelper.submitGroupCreation();
        navigationHelper.groupPage();
    }

}
