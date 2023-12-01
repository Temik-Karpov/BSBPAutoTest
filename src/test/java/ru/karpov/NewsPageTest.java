package ru.karpov;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
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
    public void selectNewsYearTest() throws InterruptedException {
        newsPage.inputSelect("2022");

        //new WebDriverWait(driver, Duration.ofMillis(2000));
        Thread.sleep(2000);

        assertThat(newsPage.getLastDateOfNews().getText())
                .as("Дата должна содержать указанный год")
                .endsWith("2022")
                .doesNotContain("2023");

        assertThat(newsPage.getDateOfNews().getText())
                .as("Дата должна содержать указанный год")
                .endsWith("2022")
                .doesNotContain("2023");
    }

    @Test
    public void selectNewsFilterTest() throws InterruptedException {
        newsPage.clickNewsFilterValueButton();

        //new WebDriverWait(driver, Duration.ofMillis(2000));
        Thread.sleep(2000);

        final Integer newsCategoryCount = newsPage.newsCategoryCount("ЧАСТНЫМ КЛИЕНТАМ");

        Assertions.assertThat(newsPage.getNewsCategoryValues())
                .filteredOn(value ->
                value.getText().contains("ЧАСТНЫМ КЛИЕНТАМ"))
                .extracting(value -> value.getText())
                .containsOnly("ЧАСТНЫМ КЛИЕНТАМ");

        Assertions.assertThat(newsPage.getNewsList())
                .as("Кол-во новостей должно совпадать с кол-вом отображаемых категорий")
                .hasSize(newsCategoryCount);
    }


    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
