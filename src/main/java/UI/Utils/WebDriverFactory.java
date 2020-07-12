package UI.Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import static UI.Utils.Constans.IMP_WAIT_TIME_SEC;
import static UI.Utils.Constans.WINDOW_SIZE;

public class WebDriverFactory {

    private static String selenoidIP = System.getProperty("ip", "18.219.122.255");


    private WebDriverFactory() {
    }

    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>();

    public static WebDriver configDriver(String browser) {

        WebDriver webDriver = null;
        try {

            switch (browser.toLowerCase()) {
                case "docker":
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setBrowserName("chrome");
                    capabilities.setVersion("81.0");
                    capabilities.setCapability("enableVNC", true);
                    capabilities.setCapability("enableVideo", false);
                    webDriver = new RemoteWebDriver(
                            URI.create("http://" + selenoidIP + ":4444/wd/hub").toURL(),
                            capabilities);

                    break;
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = setChromeOptions();
                    webDriver = new ChromeDriver(chromeOptions);

                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = setFFOptions();
                    webDriver = new FirefoxDriver(firefoxOptions);

                    break;

                default:
                    throw new RuntimeException("Driver is not implemented for browser: " + browser);
            }
            webDriver.manage().timeouts().implicitlyWait(IMP_WAIT_TIME_SEC, TimeUnit.SECONDS);
            webDriver.manage().window().maximize();

        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return webDriver;
    }

    public static void setDriver(String browser) {
        threadDriver.set(configDriver(browser));
    }

    public static WebDriver getDriver() {
        return threadDriver.get();
    }

    private static ChromeOptions setChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        options.addArguments(WINDOW_SIZE);
        options.setHeadless(true);
        return options;
    }

    private static FirefoxOptions setFFOptions() {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        options.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);
        firefoxBinary.addCommandLineOptions("--headless");
        options.setBinary(firefoxBinary);
        return options;
    }

    public static void quitDriver() {
        threadDriver.get().quit();
        threadDriver.set(null);
    }

}
