package addressbook.tests;
/***
 * by rootkoz
 * >(((*>
 */

import org.testng.annotations.Test;

public class GroupDeletion extends TestBase{

    @Test
    public void groupDeletionTest() {
        app.getNavigationHelper().groupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroup();
        app.getNavigationHelper().groupPage();
    }

}
