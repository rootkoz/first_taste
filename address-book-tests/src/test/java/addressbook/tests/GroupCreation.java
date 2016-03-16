package addressbook.tests;
/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class GroupCreation extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().groupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();

        GroupData group = new GroupData("Z", "headSpins", "g-name");

        app.getGroupHelper().createGroup(group);
        app.getNavigationHelper().groupPage();

        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size()+1);

        before.add(group);

        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
