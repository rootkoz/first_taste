package addressbook.tests;
/***
 * by rootkoz
 * >(((*>
 */

import org.testng.annotations.Test;

public class GroupDeletion extends TestBase{

    @Test
    public void groupDeletionTest() {
        navigationHelper.groupPage();
        groupHelper.selectGroup();
        groupHelper.deleteSelectedGroup();
        navigationHelper.groupPage();
    }

}
