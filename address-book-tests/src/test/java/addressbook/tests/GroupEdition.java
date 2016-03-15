package addressbook.tests;
/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class GroupEdition extends TestBase {

    @Test
    public void testGroupEdition() {

        app.getNavigationHelper().groupPage();
        createGroupIfNotExist(new GroupData(null, "4 Edition", "headSpins", "aaa"));

        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData groupData = new GroupData(before.get(before.size()-1).getId(), "4 Edition", "headSpins", "aaa");

        app.getGroupHelper().selectGroup(before.size()-1);
        app.getGroupHelper().editSelectedGroup();
        app.getGroupHelper().modifyGroup(groupData);
        app.getGroupHelper().submitGroupUpdate();
        app.getNavigationHelper().groupPage();

        List<GroupData> after = app.getGroupHelper().getGroupList();

        before.remove(before.size()-1);
        before.add(groupData);
        Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

    }
}
