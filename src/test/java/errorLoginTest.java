import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class errorLoginTest {
    public WebDriver driver;

    @BeforeMethod
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test(description = "Проверка ошибки входа при неверном username", priority = 0)
    public void testErrorlogin() {

        //Открываем страницу Test
        driver.get("http://the-internet.herokuapp.com/login");

        //Печатаем титул страницы в лог
        System.out.println("Page title is: " + driver.getTitle());

        //Ищем поле ввода Username
        WebElement username = driver.findElement(By.id("username"));

        //Вводим некорректный логин в поле Username
        username.sendKeys("tomsmith1");

        //Ищем поле ввода Password
        WebElement password = driver.findElement(By.id("password"));

        //Вводим в поле Password
        password.sendKeys("SuperSecretPassword!");

        //Ищем кнопку Login
        WebElement buttonLogin = driver.findElement(By.className("radius"));

        //Нажимаем кнопку Login
        buttonLogin.sendKeys(Keys.ENTER);

        //Ассерт того что выполнен вход
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='flash error']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Your username is invalid!')]")).isDisplayed());
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
