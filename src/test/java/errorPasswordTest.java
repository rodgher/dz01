import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import static pages.LoginPage.*;

public class errorPasswordTest {
    public WebDriver driver;
    public LoginPage newLoginPage;

    @BeforeMethod
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        newLoginPage = new LoginPage(driver);
    }

    @Test(description = "Проверка ошибки входа при неверном password", priority = 0)
    public void testIncorrectPassword() {

        //Открываем страницу Test/login
        openLoginPage(driver);

        //Вводим логин в поле Username
        enterField(usernameField, "tomsmith");

        //Вводим в поле не корректный Password
        enterField(passwordField, "SuperSecretPassword!1");

        //Нажимаем Enter
        pressEnterForElement(buttonLogin);

        //Ассерт ошибки входа - Не верный password
        checkElementDisplayed(errorPopupLogin);
        checkElementDisplayed(errorMessagePasswordLogin);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
