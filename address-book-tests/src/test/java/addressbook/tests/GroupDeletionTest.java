package addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase{

    @Test
    public void groupDeletionTest() {
        groupHelper.groupPage();
        groupHelper.selectGroup();
        groupHelper.deleteSelectedGroup();
        groupHelper.groupPage();
    }

}
