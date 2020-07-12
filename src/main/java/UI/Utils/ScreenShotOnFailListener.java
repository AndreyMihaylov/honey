package UI.Utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static UI.Utils.CommonUtils.addInfo;


public class ScreenShotOnFailListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        addInfo("++++++++++Test start :"+ result.getName()+"++++++++++");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        addInfo("+++++++++Test Successful :"+ result.getName()+"++++++++++");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        addError(result.getName()+"++++++++++++Test Failed. Something should be verified! Screenshot is taken++++++++++");
        CommonUtils.makeScreenshotAttachment("test_fail");
    }

    private void addError(String s) {
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
    }


}
