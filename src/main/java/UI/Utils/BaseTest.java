package UI.Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import static UI.Utils.Constans.URL_MAIN;
import static UI.Utils.Constans.WINDOW_SIZE;
import java.net.URI;

public class BaseTest {
    private static WebDriver webDriver;
    private static WebDriverWait wait;

    @BeforeTest
    @Parameters("browser")
    public void setUp(@Optional("docker") String browser) {

//        BROWSER = browser;
        initialize(browser);
        open(URL_MAIN);
        getWebdriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    public void open(String url) {
        getWebdriver().get(url);
    }

    public void configDriver(String browser) {

        try {
            switch (browser.toLowerCase()) {
                case "docker" :
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setBrowserName("chrome");
                    capabilities.setVersion("81.0");
                    capabilities.setCapability("enableVNC", true);
                    capabilities.setCapability("enableVideo", false);
                        webDriver = new RemoteWebDriver(
                                URI.create("http://13.59.240.188:4444/wd/hub").toURL(),
                                capabilities);

                    break;
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
//                    chromeOptions.setHeadless(true);
                    chromeOptions.addArguments(WINDOW_SIZE);
                    webDriver = new ChromeDriver(chromeOptions);

                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    FirefoxBinary firefoxBinary = new FirefoxBinary();
//                    firefoxBinary.addCommandLineOptions("--headless");
                    firefoxOptions.setBinary(firefoxBinary);
                    webDriver = new FirefoxDriver(firefoxOptions);

                    break;

                default:
                    throw new RuntimeException("Driver is not implemented for browser: " + browser);
            }

        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }


    public void initialize(String browser) {
        setWebdriver(browser);
        setWait(3);
    }

    public static WebDriver getWebdriver() {

        return webDriver;
    }

    public void setWebdriver(String browser) {
        configDriver(browser);
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    public void setWait(int timeSeconds) {
        wait = new WebDriverWait(getWebdriver(),timeSeconds);
    }

    @AfterTest
    public void closeDriver() {
        if (getWebdriver() != null) {
            getWebdriver().quit();
        }
    }
}
