import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.LoginPage;

import static helpers.testLoginData.trueLogin;
import static helpers.testLoginData.truePassword;
import static pages.LoginPage.*;

public class loginTest {

    public WebDriver driver;
    public LoginPage newLoginPage;

    @BeforeMethod
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        newLoginPage = new LoginPage(driver);
    }

    @Test(description = "Проверка корректного входа", priority = 0)
    public void testLoginSuccess() {

        //Открываем страницу Test/login
        openLoginPage(driver);

        //Вводим в поле Username
        enterField(usernameField, trueLogin);

        //Вводим в поле Password
        enterField(passwordField, truePassword);

        //Нажимаем Enter
        pressEnterForElement(buttonLogin);

        //Ассерт того что выполнен успешный вход
        checkElementDisplayed(successPopupLogin);
        checkElementDisplayed(successMessageLogin);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
