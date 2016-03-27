package addressbook.tests;
/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class GroupEdition extends TestBase {

    @BeforeMethod
    public void preConditions() {
        app.goTo().groupPage();
        createGroupIfNotExist(groupDummy);
    }

    @Test
    public void testGroupEdition() {
        Set<GroupData> before = app.group().all();
        GroupData modifyGroup = before.iterator().next();

        GroupData groupData = new GroupData().withId(modifyGroup.getId()).withHeader("Edited").withName("backspins").withFooter("o-O");

        app.group().modify(groupData);

        app.goTo().groupPage();

        Set<GroupData> after = app.group().all();
        Assert.assertEquals(before.size(), after.size());

        before.remove(modifyGroup);
        before.add(groupData);

        Assert.assertEquals(after, before);
    }


}
