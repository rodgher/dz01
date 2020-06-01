//import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class firstExampleClean {

    public WebDriver driver;

    @BeforeMethod
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }


    @Test(description = "Тут написано описание того что делает тест в вольной форме", priority = 0)
    public void testOpenGoogleAndCheck() {

        //Открываем на весь экран
//        driver.manage().window().maximize();

        //Открываем страницу Гугла
        driver.get("http://www.google.com");

        //Печатаем титул страницы в лог
        System.out.println("Page title is: " + driver.getTitle());

        //Ищем поле ввода формы поиска
        WebElement element = driver.findElement(By.name("q"));

        //Вводим в поиск selenium
        element.sendKeys("selenium");

        //Запускаем поиск
        element.sendKeys(Keys.ENTER);

        //Ассерт того что div с результатами поиска вообще отображается
        Assert.assertTrue(driver.findElement(By.id("search")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Selenium — Википедия')]")).isDisplayed());

    }




    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }







}
