package addressbook.tests;
/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeletion extends TestBase {

    @BeforeMethod
    public void preConditions() {
        createGroupIfNotExist(groupDummy);
    }

    @Test
    public void testGroupDeletion() {
        app.goTo().groupPage();
        createGroupIfNotExist(groupDummy);

        List<GroupData> before = app.group().list();
        int index = before.size() - 1;

        app.group().delete(index);
        app.goTo().groupPage();
        List<GroupData> after = app.group().list();

        before.remove(index);
        Assert.assertEquals(after, before);
    }

}
