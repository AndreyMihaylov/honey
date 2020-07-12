package UI.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static UI.Utils.BaseTest.webDriver;
import static org.openqa.selenium.support.PageFactory.initElements;

public class HomePage extends BasePage {

    @FindBy(id = "header-log-in")
    WebElement logIn;

    @FindBy(xpath = "//span[contains(text(),'Log Out')]")
    WebElement logOut;

    @FindBy(id = "auth-login-modal")
    WebElement loginWithEmail;

    @FindBy(xpath = "//input[@id='email-auth-modal']")
    WebElement inputEmail;

    @FindBy(xpath = "//input[@id='pwd-auth-modal']")
    WebElement inputPassword;

    @FindBy(xpath = "//div[contains(@class,'t-toggleContainer')]")
    WebElement extendCategories;

    @FindBy(xpath = "//div[contains(@class,'t-userProfile')]")
    WebElement profile;

    @FindBy(xpath = "//div[@id='hamburger-join']")
    WebElement join;

    public HomePage() {
        initElements(webDriver, this);
    }

    @Step("Click on login link")
    public HomePage clickLogIn() {
        click(logIn);
        return this;
    }

    @Step("Extend profile categories")
    public HomePage clickExtendCategories() {
        click(extendCategories);
        return this;
    }

    @Step("Click on 'Join'")
    public HomePage clickJoin() {
        click(join);
        return this;
    }

    @Step("Click on 'Log Out'")
    public HomePage clickLogOut() {
        click(profile);
        click(logOut);
        return this;
    }

    @Step("Click on 'Login with Email' in new window")
    public HomePage clickLogInWithEmail() {
        click(loginWithEmail);
        return this;
    }

    @Step("Fill out email with email: {0}")
    public HomePage fillOutEmail(String email) {
        type(inputEmail, email);
        return this;
    }

    @Step("Fill out password with password: {0}")
    public HomePage fillOutPassword(String password) {
        type(inputPassword, password);
        return this;
    }

    @Step("Check login success")
    public boolean successfulLogIn() {
        return profile.isDisplayed();
    }

    @Step("Check logout success")
    public boolean successfulLogOut() {
        return logIn.isDisplayed();
    }


}
