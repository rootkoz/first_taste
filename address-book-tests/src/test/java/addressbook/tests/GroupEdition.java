package addressbook.tests;
/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupEdition extends TestBase {

    @BeforeMethod
    public void preConditions() {
        createGroupIfNotExist(groupDummy);
    }

    @Test
    public void testGroupEdition() {
        app.goTo().groupPage();



        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        GroupData groupData = new GroupData(before.get(index).getId(), "Edited", "e-headSpins", "e-aaa");

        app.group().modify(index, groupData);

        app.goTo().groupPage();

        List<GroupData> after = app.group().list();
        Assert.assertEquals(before.size(), after.size());

        before.remove(index);
        before.add(groupData);

        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }


}
