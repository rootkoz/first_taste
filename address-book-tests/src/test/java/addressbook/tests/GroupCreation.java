package addressbook.tests;
/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class GroupCreation extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Set<GroupData> before = app.group().all();

        app.group().create(groupDummy);
        app.goTo().groupPage();

        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        groupDummy.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());

        before.add(groupDummy);
        Assert.assertEquals(before, after);
    }
}
