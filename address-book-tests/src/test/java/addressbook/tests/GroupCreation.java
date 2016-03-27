package addressbook.tests;
/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.Groups;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreation extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();

        app.group().create(groupDummy);
        app.goTo().groupPage();

        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size()+1));

        assertThat(after, equalTo
                (before.withAdded(groupDummy.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
