package addressbook.tests;
/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class GroupEdition extends TestBase {

    @BeforeMethod
    public void preConditions() {
        app.goTo().groupPage();
        createGroupIfNotExist(groupDummy);
    }

    @Test
    public void testGroupEdition() {
        Groups before = app.group().all();
        GroupData modifyGroup = before.iterator().next();

        GroupData groupData = new GroupData().withId(modifyGroup.getId()).withHeader("Edited").withName("backspins").withFooter("o-O");

        app.group().modify(groupData);

        app.goTo().groupPage();

        Groups after = app.group().all();
        assertEquals(before.size(), after.size());

        assertThat(after, equalTo(before.without(modifyGroup).withAdded(groupData)));
    }


}
