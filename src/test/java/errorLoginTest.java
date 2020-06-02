import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import static pages.LoginPage.*;

public class errorLoginTest {
    public WebDriver driver;
    public LoginPage newLoginPage;

    @BeforeMethod
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        newLoginPage = new LoginPage(driver);
    }

    @Test(description = "Проверка ошибки входа при неверном username", priority = 0)
    public void testIncorrectUsername() {

        //Открываем страницу Test/login
        openLoginPage(driver);

        //Вводим в поле не корректный Username
        enterField(usernameField, "tomsmith1");

        //Вводим в поле Password
        enterField(passwordField, "SuperSecretPassword!");

        //Нажимаем Enter
        pressEnterForElement(buttonLogin);

        //Ассерт ошибки входа - Не верный username
        checkElementDisplayed(errorPopupLogin);
        checkElementDisplayed(errorMessageUsernameLogin);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
