package addressbook.tests;
/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletion extends TestBase {

    @BeforeMethod
    public void preConditions() {
        app.goTo().groupPage();
        createGroupIfNotExist(groupDummy);
    }

    @Test
    public void testGroupDeletion() {
        Groups before = app.group().all();
        GroupData deleteGroup = before.iterator().next();

        app.group().delete(deleteGroup);
        app.goTo().groupPage();
        Groups after = app.group().all();

        assertEquals(after.size(), before.size()-1);
        assertThat(after, equalTo(before.without(deleteGroup)));
    }

}
