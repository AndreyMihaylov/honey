package UITests;

import UI.Pages.HomePage;
import UI.Utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import static UI.Pages.BasePage.sleep;

public class LoginTest extends BaseTest {

    HomePage homePage;

    @Test
    public void logIn(){
        homePage = new HomePage();
        homePage.clickLogIn()
                .clickLogInWithEmail()
                .fillOutEmail("mikhailov.andrei.qa@gmail.com")
                .fillOutPassword("Qwerty1234")
                .clickLogInWithEmail();
        Assert.assertTrue(homePage.successfulLogIn(),"Fail to logIn");

        sleep(8);
    }

    @Test(dependsOnMethods = {"logIn"})
    public void logOut(){
        homePage = new HomePage();

    }
}
