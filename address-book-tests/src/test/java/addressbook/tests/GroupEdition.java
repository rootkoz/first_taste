package addressbook.tests;
/***
 * by rootkoz
 * >(((*>
 */

import org.testng.annotations.Test;

public class GroupEdition extends TestBase{

    @Test
    public void editGroupTest() {
        app.getNavigationHelper().groupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().editGroup("EDITED", "e-GROUP", "-321-");
        app.getNavigationHelper().groupPage();
    }

}
