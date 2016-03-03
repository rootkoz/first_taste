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
        app.getNavigationHelper().groupPage();
        app.getGroupHelper().createNewGroup();
        app.getGroupHelper().fillGroupForm(new GroupData("G5", "G5-header", "G5-footer"));
        app.getGroupHelper().submitGroupCreation();
        app.getNavigationHelper().groupPage();
    }

}
