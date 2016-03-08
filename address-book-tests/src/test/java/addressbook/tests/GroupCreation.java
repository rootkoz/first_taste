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
        app.getGroupHelper().createGroup(new GroupData("Z", "headSpins", "g-name"));
        app.getNavigationHelper().groupPage();
    }

}
