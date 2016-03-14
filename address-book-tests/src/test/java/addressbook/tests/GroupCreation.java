package addressbook.tests;
/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class GroupCreation extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().groupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().createGroup(new GroupData("Z", "headSpins", "g-name"));
        app.getNavigationHelper().groupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();

        Assert.assertEquals(after.size(), before.size()+1);
    }

}
