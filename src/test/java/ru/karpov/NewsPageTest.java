package ru.karpov;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.karpov.pageObjects.NewsPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.Duration;

public class NewsPageTest {
    public static NewsPage newsPage;
    public static WebDriver driver;

    @BeforeClass
    public static void setup()
    {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfProperties.getProperty("newspage"));

        newsPage = new NewsPage(driver);
    }

    @Test
    public void selectNewsYearTest() {
        newsPage.inputSelect("2022");

        new WebDriverWait(driver, Duration.ofMillis(2000));

        assertThat(newsPage.getLastDateOfNews().getText()).contains("2022");
        assertThat(newsPage.getDateOfNews().getText()).contains("2022");
    }

    @Test
    public void selectNewsFilterTest() {
        newsPage.clickNewsFilterValueButton();

        new WebDriverWait(driver, Duration.ofMillis(2000));

        final Integer newsCategoryCount = newsPage.newsCategoryCount("ЧАСТНЫМ КЛИЕНТАМ");

        assertThat(newsCategoryCount).isEqualTo(newsPage.getNewsList().size());
    }


    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
