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

public class GroupDeletion extends TestBase {

    @BeforeMethod
    public void preConditions() {
        app.goTo().groupPage();
        createGroupIfNotExist(groupDummy);
    }

    @Test
    public void testGroupDeletion() {

        createGroupIfNotExist(groupDummy);

        Set<GroupData> before = app.group().all();
        GroupData deleteGroup = before.iterator().next();

        app.group().delete(deleteGroup);
        app.goTo().groupPage();
        Set<GroupData> after = app.group().all();

        before.remove(deleteGroup);
        Assert.assertEquals(after, before);
    }

}
