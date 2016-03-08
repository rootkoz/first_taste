package addressbook.tests;
/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupDeletion extends TestBase{

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().groupPage();
        createGroupIfNotExist(new GroupData("4 Deletion", "headSpins", "aaa"));
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroup();
        app.getNavigationHelper().groupPage();
    }



}
