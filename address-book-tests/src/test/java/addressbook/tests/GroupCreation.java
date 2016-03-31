package addressbook.tests;
/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.GroupData;
import addressbook.model.Groups;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreation extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
        String line = reader.readLine();
        String xml="";

        while (line != null){
            xml += line;
            line = reader.readLine();
        }
        XStream xStream = new XStream();
        xStream.processAnnotations(GroupData.class);
        List<GroupData> groups = (List<GroupData>)xStream.fromXML(xml);
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();


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
