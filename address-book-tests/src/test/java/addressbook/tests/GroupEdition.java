package addressbook.tests;
/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupEdition extends TestBase{

    @Test
    public void testGroupEdition() {
        app.getNavigationHelper().groupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().editSelectedGroup();
        app.getGroupHelper().modifyGroup(new GroupData("EDITED", "e-GROUP", "-321-"));
        app.getGroupHelper().submitGroupUpdate();
        app.getNavigationHelper().groupPage();
    }

}
