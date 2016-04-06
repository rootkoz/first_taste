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

public class GroupEdition extends TestBase {

    @BeforeMethod
    public void preConditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            createGroupIfNotExist(groupDummy);
        }
    }

        @Test
        public void testGroupEdition () {
            Groups before = app.db().groups();
            GroupData modifyGroup = before.iterator().next();

            GroupData groupData = new GroupData().withId(modifyGroup.getId()).withHeader("Edited").withName("backspins").withFooter("o-O");

            app.goTo().groupPage();
            app.group().modify(groupData);
            app.goTo().groupPage();

            assertThat(app.group().count(), equalTo(before.size()));
            Groups after = app.db().groups();


            assertThat(after, equalTo(before.without(modifyGroup).withAdded(groupData)));

            verifyGroupListOnUI();
        }


}
