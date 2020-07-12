package UITests;

import UI.Pages.HomePage;
import UI.Utils.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static UI.Pages.BasePage.sleep;

public class LoginTest extends BaseTest {

    HomePage homePage;

    @Description("Login test")
    @Story("Smoke")
    @Parameters({"name","pswd"})
    @Test
    public void logIn(@Optional("none")String name, @Optional("none") String pswd){
        homePage = new HomePage();
        homePage.clickLogIn()
                .clickLogInWithEmail()
                .fillOutEmail(name)
                .fillOutPassword(pswd)
                .clickLogInWithEmail();
        Assert.assertTrue(homePage.successfulLogIn(),"Fail to logIn");

        sleep(8);
    }

    @Description("Logout test. Run after login test")
    @Test(dependsOnMethods = {"logIn"})
    public void logOut(){
        homePage = new HomePage();
        homePage.clickExtendCategories().clickLogOut().successfulLogOut();
    }
}
