package addressbook.tests;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.annotations.Test;

import java.sql.*;


@Test
public class DBCtest {

    @Test
    public void testDBC(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?" + "user=root&password=");

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select group_name, group_id from group_list");
            Groups groups = new Groups();
            while (rs.next()){
                groups.add(new GroupData().withName(rs.getString("group_name")).withId(rs.getInt("group_id")));
            }

            rs.close();
            st.close();
            conn.close();
            System.out.println(groups);

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

}
