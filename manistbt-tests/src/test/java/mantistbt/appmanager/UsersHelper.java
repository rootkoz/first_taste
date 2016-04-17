package mantistbt.appmanager;

import org.openqa.selenium.By;

/***
 * by rootkoz
 * >(((*>
 */


public class UsersHelper extends HelperBase{

    public UsersHelper(AppManager app) {
        super(app);
    }

    public void startRegistration(String user, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"),user);
        type(By.name("email"),email);
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
        type(By.name("username"),user);
        type(By.name("password"),password);
        click(By.cssSelector("input[value='Login']"));
    }

    public void manageUsers(){
        click(By.xpath("//span[1]/a"));
    }

    public void selectSecondUser() {
        click(By.xpath("//tr[4]/td[1]/a")); // first user after admin
    }

    public  void submitResetPassword(){
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void resetUserPassword() {
        manageUsers();
        selectSecondUser();
        submitResetPassword();
    }


}
