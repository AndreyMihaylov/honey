package UITests;

import UI.Pages.HomePage;
import UI.Utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import static UI.Utils.Constans.WINDOW_SIZE;

public class SingUpTest extends BaseTest {

    HomePage homePage;

    @BeforeSuite
    public void setUp() {
        WINDOW_SIZE = "--window-size=1000,1000";
    }

    @Test
    public void singUp() {
        homePage = new HomePage();
        homePage.clickExtendCategories()
                .clickJoin()
                .clickLogInWithEmail()
                .fillOutEmail("mikhailov.andrei.qa1@gmail.com")
                .fillOutPassword("Qwerty1234")
                .clickLogInWithEmail();
        Assert.assertTrue(homePage.successfulLogIn(), "Fail to signUp");


    }
}