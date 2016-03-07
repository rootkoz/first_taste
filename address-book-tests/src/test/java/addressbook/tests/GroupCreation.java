package addressbook.tests;
/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.GroupData;
import org.testng.annotations.Test;


public class GroupCreation extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().groupPage();
        app.getGroupHelper().createNewGroup();
        app.getGroupHelper().modifyGroup(new GroupData("Z", "headSpins", "g-name"));
        app.getGroupHelper().submitGroupCreation();
        app.getNavigationHelper().groupPage();
    }

}
