package addressbook.tests;
/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeletion extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().groupPage();
        createGroupIfNotExist(groupDummy);

        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().deleteSelectedGroup();
        app.getNavigationHelper().groupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();

        before.remove(before.size() - 1);
        Assert.assertEquals(after, before);
    }
}
