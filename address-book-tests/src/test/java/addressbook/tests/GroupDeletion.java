package addressbook.tests;
/***
 * by rootkoz
 * >(((*>
 */

import org.testng.annotations.Test;

public class GroupDeletion extends TestBase{

    @Test
    public void groupDeletionTest() {
        groupHelper.groupPage();
        groupHelper.selectGroup();
        groupHelper.deleteSelectedGroup();
        groupHelper.groupPage();
    }

}
