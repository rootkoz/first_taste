package mantistbt.appmanager;

import org.openqa.selenium.By;

/***
 * by rootkoz
 * >(((*>
 */


public class UsersHelper extends HelperBase {

    private String username;

    public UsersHelper(AppManager app) {
        super(app);
    }

    public void startRegistration(String user, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), user);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Signup']"));
    }


    public void finishUpdate(String link, String password) {
        wd.get(link);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
    }

    public void adminLogin(String user, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), user);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Login']"));
    }

    public void manageUsers() {
        click(By.xpath("//span[1]/a"));
    }

    public void selectUser() {
        int iter = 3; // - first needed <tr>
        iter = findNonRoleUser(iter, "administrator"); // first non admin user
        click(By.xpath("//tr[" + iter + "]/td[1]/a"));
        this.username = wd.findElement(By.xpath("//tr[2]/td[2]/input")).getAttribute("value");
    }

    private int findNonRoleUser(int iter, String role) {
        while (wd.findElement(By.xpath("//tr[" + iter + "]/td[4]")).getText().equals(role)) {
            iter += 1;
        }
        return iter;
    }

    public void submitResetPassword() {
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public String resetUserPassword() {
        manageUsers();
        selectUser();
        submitResetPassword();
        return username;
    }


}
