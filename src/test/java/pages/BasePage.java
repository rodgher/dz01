package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;

import static helpers.testAllureListener.getTestMethodName;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import static io.github.bonigarcia.wdm.DriverManagerType.FIREFOX;

public class BasePage {

    public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
    public WebDriver driver;

    public static synchronized WebDriver getDriver() {
        return tdriver.get();
    }

    @Step("Создание веб. драйвера с заданными параметрами")
    public WebDriver initialize_driver() {

        WebDriverManager.getInstance(CHROME).config().setChromeDriverVersion("79");
        WebDriverManager.getInstance(CHROME).setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
        tdriver.set(driver);
        return getDriver();
    }

    @Step("Закрытие драйвера + скриншот если тест зафейлен")
    public static void makeScreenOnTestFail(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {

            WebDriver driver = BasePage.getDriver();
            // Allure ScreenShotRobot and SaveTestLog
            if (driver instanceof WebDriver) {
                System.out.println("Screenshot captured for test case:" + getTestMethodName(result));
                saveScreenshotPNG(driver);
            }
            // Save a log on allure.
            saveTextLog(getTestMethodName(result) + " failed and screenshot taken!");
        }
    }

    // Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    //Image attachments for Allure
    @Step("Снятие скриншота в аллюр")
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
