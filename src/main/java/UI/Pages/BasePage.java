package UI.Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static UI.Utils.BaseTest.getWait;
import static UI.Utils.BaseTest.getWebdriver;


public abstract class BasePage {


    public void waitForVisability(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClick(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForSelected(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeSelected(element));
    }

    public BasePage click(WebElement element) {

        try {
            waitForClick(element);
            element.click();
        } catch (Exception e) {
            jsClick(element);

        }
        return this;
    }

    public BasePage jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getWebdriver();
        js.executeScript("arguments[0].click();", element);
        return this;
    }

    public BasePage type(WebElement element, String text) {
        waitForVisability(element);
        element.clear();
        element.sendKeys(text);
        return this;
    }

    public static void sleep(int sec){
        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
