package addressbook.tests;
/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupEdition extends TestBase{

    @Test
    public void testGroupEdition() {
        app.getNavigationHelper().groupPage();
        createGroupIfNotExist(new GroupData("4 Edition", "headSpins", "aaa"));
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().editSelectedGroup();
        app.getGroupHelper().modifyGroup(new GroupData("Edited", "backSpin", "0aaa"));
        app.getGroupHelper().submitGroupUpdate();
        app.getNavigationHelper().groupPage();
    }

}
