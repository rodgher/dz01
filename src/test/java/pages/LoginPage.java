package pages;

import io.qameta.allure.Step;
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
    public static WebElement successPopupLogin;

    @FindBy(xpath = "//*[@class='flash error']")
    public static WebElement errorPopupLogin;

    @FindBy(xpath = "//*[contains(text(), 'You logged into a secure area!')]")
    public static WebElement successMessageLogin;

    @FindBy(xpath = "//*[contains(text(), 'Your username is invalid!')]")
    public static WebElement errorMessageUsernameLogin;

    @FindBy(xpath = "//*[contains(text(), 'Your password is invalid!')]")
    public static WebElement errorMessagePasswordLogin;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //открываем ссылку
    @Step("Открываем тестовую страницу")
    public static void openLoginPage(WebDriver driver) {

        driver.get("http://the-internet.herokuapp.com/login");
    }

    @Step("Нажимаем ENTER")
    public static void pressEnterForElement(WebElement buttonLogin) {

        buttonLogin.sendKeys(Keys.ENTER);
    }

    @Step("Проверяем видимость элемента")
    public static void checkElementDisplayed(WebElement element) {
        Assert.assertTrue(element.isDisplayed());
    }

    @Step("Вводим в поле")
    public static void enterField(WebElement element, String value) {

        element.sendKeys(value);
    }
}
