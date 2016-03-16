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

public class GroupEdition extends TestBase {

    @Test
    public void testGroupEdition() {

        app.getNavigationHelper().groupPage();
        createGroupIfNotExist(new GroupData("4 Edition", "headSpins", "aaa"));

        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData groupData = new GroupData(before.get(before.size()-1).getId(), "4 Edition", "headSpins", "aaa");

        app.getGroupHelper().selectGroup(before.size()-1);
        app.getGroupHelper().editSelectedGroup();
        app.getGroupHelper().modifyGroup(groupData);
        app.getGroupHelper().submitGroupUpdate();
        app.getNavigationHelper().groupPage();

        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(before.size(), after.size());

        before.remove(before.size()-1);
        before.add(groupData);


        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(after, before);

    }
}
