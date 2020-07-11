package UI.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static UI.Utils.BaseTest.getWebdriver;
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
        initElements(getWebdriver(), this);
    }

    public HomePage clickLogIn() {
        click(logIn);
        return this;
    }

    public HomePage clickExtendCategories() {
        click(extendCategories);
        return this;
    }

    public HomePage clickJoin() {
        click(join);
        return this;
    }

    public HomePage clickLogOut() {
        click(profile);
        click(logOut);
        return this;
    }

    public HomePage clickLogInWithEmail() {
        click(loginWithEmail);
        return this;
    }

    public HomePage fillOutEmail(String email) {
        type(inputEmail, email);
        return this;
    }

    public HomePage fillOutPassword(String password) {
        type(inputPassword, password);
        return this;
    }

    public boolean successfulLogIn() {
        return profile.isDisplayed();
    }

    public boolean successfulLogOut() {
        return logIn.isDisplayed();
    }


}
