import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.BasePage;
import pages.LoginPage;

import static helpers.testLoginData.trueLogin;
import static helpers.testLoginData.truePassword;
import static pages.BasePage.makeScreenOnTestFail;
import static pages.LoginPage.*;

public class successLoginTest {

    public WebDriver driver;
    public pages.BasePage BasePage;
    public LoginPage newLoginPage;

    @BeforeMethod
    public void setupClass() {
        BasePage = new BasePage();
        driver = BasePage.initialize_driver();
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
    public void teardown(ITestResult result) {
        makeScreenOnTestFail(result);
        if (driver != null) {
            driver.quit();
        }
    }
}
