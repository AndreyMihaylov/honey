package UITests;

import UI.Pages.HomePage;
import UI.Utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static UI.Utils.Constans.URL_MAIN;
import static UI.Utils.Constans.WINDOW_SIZE;

public class SingUpTest extends BaseTest {

    HomePage homePage;

    @BeforeTest
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        WINDOW_SIZE = "--window-size=1000,1000";
        initialize(browser);
        open(URL_MAIN);
        getWebdriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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
