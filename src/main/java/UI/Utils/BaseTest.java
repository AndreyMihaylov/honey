package UI.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static UI.Utils.Constans.URL_MAIN;


public class BaseTest {

    public static WebDriver webDriver;
    private static WebDriverWait wait;

    @BeforeTest
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        webDriver = WebDriverFactory.getInstance().getDriver(browser);
        setWait(3);
        open(URL_MAIN);

    }

    public void open(String url) {
        webDriver.get(url);
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    public void setWait(int timeSeconds) {
        wait = new WebDriverWait(webDriver, timeSeconds);
    }

    @AfterTest
    public void closeDriver() {
        WebDriverFactory.getInstance().quitDriver();
    }
}
