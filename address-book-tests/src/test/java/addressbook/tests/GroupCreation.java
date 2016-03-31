package addressbook.tests;
/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreation extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(";");
            list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
            line = reader.readLine();

        }

//        list.add(new Object[] {new GroupData().withName("testa 1").withHeader("Head 1").withFooter("F 1")});
//        list.add(new Object[] {new GroupData().withName("testa 3").withHeader("Head 2").withFooter("F 3")});
//        list.add(new Object[] {new GroupData().withName("testa 4").withHeader("Head 5").withFooter("F 4")});
        return list.iterator();

    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData groups) {
        app.goTo().groupPage();
        Groups before = app.group().all();

        app.group().create(groups);
        app.goTo().groupPage();

        assertThat(app.group().count(), equalTo(before.size() + 1));

        Groups after = app.group().all();
        assertThat(after, equalTo
                (before.withAdded(groups.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();

        app.group().create(groupDummy.withName("ba'D"));
        app.goTo().groupPage();

        assertThat(app.group().count(), equalTo(before.size()));

        Groups after = app.group().all();
        assertThat(after, equalTo(before));
    }
}
