package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {

    public WebDriver driver;

    @FindBy(id = "username")
    public static WebElement usernameField;

    @FindBy(id = "password")
    public static WebElement passwordField;

    @FindBy(className = "radius")
    public static WebElement buttonLogin;

    @FindBy(xpath = "//*[@class='flash success']")
    public static WebElement successLogin;

    @FindBy(xpath = "//*[@class='flash error']")
    public static WebElement errorLogin;

    @FindBy(xpath = "//*[contains(text(), 'You logged into a secure area!')]")
    public static WebElement successMessageLogin;

    @FindBy(xpath = "//*[contains(text(), 'Your username is invalid!')]")
    public static WebElement errorUsernameLogin;

    @FindBy(xpath = "//*[contains(text(), 'Your password is invalid!')]")
    public static WebElement errorPasswordLogin;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static void openLoginPage(WebDriver driver) {

        driver.get("http://the-internet.herokuapp.com/login");
    }

    public static void pressEnterForElement(WebElement buttonLogin) {

        buttonLogin.sendKeys(Keys.ENTER);
    }

    public static void checkElementDisplayed(WebElement element) {
        Assert.assertTrue(element.isDisplayed());
    }

    public static void enterField(WebElement element, String value) {

        element.sendKeys(value);
    }
}
