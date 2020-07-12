package UI.Utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.qameta.allure.Allure;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;

import static UI.Utils.WebDriverFactory.getDriver;


public class CommonUtils extends BaseTest {

    public static Logger logger = null;
    private static final String folderPath = (System.getProperty("user.dir") + "/reports/tests/").replaceAll("/", Matcher.quoteReplacement(File.separator));

    public static void makeScreenshotAttachment(String namePrefix) {
        TakesScreenshot scrShot =((TakesScreenshot)getDriver());
        addInfo("Taking screenshot: " + namePrefix);
        String screenName = generateScreenshotName(namePrefix);
        File src = scrShot.getScreenshotAs(OutputType.FILE);
        String folder = generateFileName(screenName);
        try {
            FileUtils.copyFile(src, new File(folder));
        } catch (IOException e) {
            e.printStackTrace();
        }
        attachScreenshot(screenName, folder);
    }

    private static String generateScreenshotName(String namePrefix) {
        return namePrefix + "_" + System.currentTimeMillis();
    }

    private static String generateFileName(String screenshotName) {
        String filePath = folderPath + screenshotName + ".png";
        addInfo("Screenshot address:\n " + filePath);
        return filePath;
    }

    private static void attachScreenshot(String screenshotName, String folder) {
        Path content = Paths.get(folder);
        try (InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment(screenshotName, is);
        } catch (Throwable e) {
            throw new Error("Wrong file address");
        }
    }

    public static void setLogger(){

        logger = LogManager.getLogger(CommonUtils.class);
    }

    public static void saveTextInfo(String message){
        Allure.addAttachment("INFO:", message);
    }

    public static void saveTextError(String message){
        Allure.addAttachment("INFO:", message);
    }

    public static void addInfo(String message){
        logger.info(message);
        saveTextInfo(message);
    }

    public static void addError(String message){
        logger.error(message);
        saveTextError(message);
    }

    public static String getCurrentDateTime(){
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(
                "MM/dd/yyyy HH:mm:ss");
        String date = formatter.format(currentDate.getTime()).replace("/", "_");
        date = date.replace(":", "_");
        return date;
    }

}
