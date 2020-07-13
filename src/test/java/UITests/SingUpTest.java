package UITests;

import UI.Pages.HomePage;
import UI.Utils.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.*;

import static UI.Utils.CommonUtils.getRandomString;
import static UI.Utils.Constans.WINDOW_SIZE;

public class SingUpTest extends BaseTest {

    HomePage homePage;

    @BeforeSuite
    public void setUp() {
        WINDOW_SIZE = "--window-size=1000,1000";
    }

    public void singUpCommon(String name,String pswd) {

        homePage = new HomePage();
        homePage.clickExtendCategories()
                .clickJoin()
                .clickLogInWithEmail()
                .fillOutEmail(name)
                .fillOutPassword(pswd)
                .clickLogInWithEmail();

    }

    @Description("SingUp test")
    @Story("Smoke SingUp")
    @Parameters({"email","pswd"})
    @Test
    public void singUp(@Optional("none")String email, @Optional("none") String pswd) {

        singUpCommon(getRandomString(4)+email,pswd);
        Assert.assertTrue(homePage.successfulLogIn(), "Fail to signUp");

    }

    @Description("SingUp test")
    @Story("Smoke SingU")
    @Parameters({"email","pswd"})
    @Test
    public void singUpNeg(@Optional("none")String email, @Optional("none") String pswd) {

        singUpCommon(email,pswd);
        Assert.assertTrue(homePage.successSignUpNeg(), "Fail to signUp");

    }
}