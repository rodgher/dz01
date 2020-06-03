import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;

import static helpers.testLoginData.errorPassword;
import static helpers.testLoginData.trueLogin;
import static pages.BasePage.makeScreenOnTestFail;
import static pages.LoginPage.*;

public class errorPasswordTest {

    public pages.BasePage BasePage;
    public WebDriver driver;
    public LoginPage newLoginPage;

    @BeforeMethod
    public void setupClass() {
        BasePage = new BasePage();
        driver = BasePage.initialize_driver();
        newLoginPage = new LoginPage(driver);
    }

    @Test(description = "Проверка ошибки входа при неверном password", priority = 0)
    public void testIncorrectPassword() {

        //Открываем страницу Test/login
        openLoginPage(driver);

        //Вводим логин в поле Username
        enterField(usernameField, trueLogin);

        //Вводим в поле не корректный Password
        enterField(passwordField, errorPassword);

        //Нажимаем Enter
        pressEnterForElement(buttonLogin);

        //Ассерт ошибки входа - Не верный password
        checkElementDisplayed(errorPopupLogin);
        checkElementDisplayed(errorMessagePasswordLogin);
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        makeScreenOnTestFail(result);
        if (driver != null) {
            driver.quit();
        }
    }
}
