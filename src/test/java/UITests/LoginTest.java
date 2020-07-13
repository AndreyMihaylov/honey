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


    public void logInCommon(String name,String pswd){
        homePage = new HomePage();
        homePage.clickLogIn()
                .clickLogInWithEmail()
                .fillOutEmail(name)
                .fillOutPassword(pswd)
                .clickLogInWithEmail();
    }

    @Description("Login test")
    @Story("Smoke")
    @Parameters({"email","pswd"})
    @Test
    public void logIn(@Optional("none")String name, @Optional("none") String pswd){

        logInCommon(name,pswd);
        Assert.assertTrue(homePage.successfulLogIn(),"Fail to logIn");
    }

    @Description("Login test negative")
    @Story("Smoke Neg")
    @Parameters({"email","pswd"})
    @Test
    public void logInNeg(@Optional("none")String name, @Optional("none") String pswd) {

        logInCommon(name, pswd);
        Assert.assertTrue(homePage.successfulWrongPswd(), "Fail to logIn");
    }



    @Description("Logout test. Run after login test")
    @Test(dependsOnMethods = {"logIn"})
    public void logOut(){

        homePage = new HomePage();
        homePage.clickExtendCategories()
                .clickLogOut();
        Assert.assertTrue(homePage.successfulLogOut(),"Fail to logIn");
    }
}
