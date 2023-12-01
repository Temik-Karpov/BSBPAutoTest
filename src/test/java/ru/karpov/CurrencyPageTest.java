package ru.karpov;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.karpov.pageObjects.CurrencyPage;

import java.time.Duration;

public class CurrencyPageTest {

    public static WebDriver driver;

    public static CurrencyPage currencyPage;

    @BeforeClass
    public static void setup()
    {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfProperties.getProperty("currencypage"));

        currencyPage = new CurrencyPage(driver);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
