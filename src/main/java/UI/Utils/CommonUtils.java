package UI.Utils;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;


public class CommonUtils extends BaseTest {

    public static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);
    private static final String folderPath = (System.getProperty("user.dir") + "/reports/tests/").replaceAll("/", Matcher.quoteReplacement(File.separator));;


    public static void makeScreenshotAttachment(String namePrefix) {
        TakesScreenshot scrShot =((TakesScreenshot)webDriver);
        logger.info("Taking screenshot: " + namePrefix);
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
        System.out.println("Screenshot address:\n " + filePath);
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

    public static void saveTextLog(String message){
        Allure.addAttachment("Log:", message);
    }

    public static void addLog(String message){
        logger.info(message);
        saveTextLog(message);
    }

}
