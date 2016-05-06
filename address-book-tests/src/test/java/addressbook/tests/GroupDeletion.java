package addressbook.tests;
/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletion extends TestBase {
    @BeforeMethod
    public void preConditions() {
        app.goTo().groupPage();
        createGroupIfNotExist(groupDummy);
    }

    @Test
    public void testGroupDeletion() {
        Groups before = app.db().groups();
        GroupData deleteGroup = before.iterator().next();

        app.group().delete(deleteGroup);
        app.goTo().groupPage();

        assertThat(app.group().count(), equalTo(before.size()-1));
        Groups after = app.db().groups();
//        assertThat(after, equalTo(before.without(deleteGroup)));
        assertThat(after, equalTo(before));//for allure test
        verifyGroupListOnUI();
    }

}
