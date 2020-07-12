package UI.Utils;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static UI.Utils.Constans.URL_MAIN;
import static UI.Utils.Constans.EXP_WAIT_TIME_SEC;
import static UI.Utils.WebDriverFactory.getDriver;
import static UI.Utils.WebDriverFactory.quitDriver;
import static UI.Utils.WebDriverFactory.setDriver;

public class BaseTest {
    private static WebDriverWait wait;

    @BeforeTest
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        setDriver(browser);
        setWait(EXP_WAIT_TIME_SEC);
        open(URL_MAIN);

    }

    public void open(String url) {
        getDriver().get(url);
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    public void setWait(int timeSeconds) {
        wait = new WebDriverWait(getDriver(), timeSeconds);
    }

    @AfterTest
    public void closeDriver() {
        quitDriver();
    }
}
