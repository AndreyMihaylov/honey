package UI.Utils;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.regex.Matcher;

import static UI.Utils.CommonUtils.getCurrentDateTime;
import static UI.Utils.CommonUtils.setLogger;
import static UI.Utils.Constans.EXP_WAIT_TIME_SEC;
import static UI.Utils.Constans.URL_MAIN;
import static UI.Utils.WebDriverFactory.*;

public class BaseTest {
    private static WebDriverWait wait;
    private String absolutePath = System.getProperty("user.dir");
    PrintStream fileStream;
    boolean debugMode = false;

    @BeforeTest
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        if (debugMode) {
            createFileStream();
            System.setOut(fileStream);
        }
        setLogger();
        setDriver(browser);
        setWait(EXP_WAIT_TIME_SEC);
        open(URL_MAIN);

    }

    public void open(String url) {
        CommonUtils.addInfo("Open "+ url);
        getDriver().get(url);
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    public void setWait(int timeSeconds) {
        wait = new WebDriverWait(getDriver(), timeSeconds);
    }

    private void createFileStream(){
        try {
            File file = new File((absolutePath +"/Logs/" + this.getClass().getName()));
            System.out.println(file.mkdir());
            fileStream = new PrintStream((file.toString()+"/"+ getCurrentDateTime() + ".txt").replaceAll("/", Matcher.quoteReplacement(File.separator)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void closeDriver() {
        quitDriver();
    }
}
