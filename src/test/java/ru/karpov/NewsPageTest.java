package ru.karpov;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.karpov.pageObjects.NewsPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.Duration;

public class NewsPageTest {
    public static NewsPage newsPage;
    public static WebDriver driver;

    @Дано("открытая новостная страница {string}")
    public void открытая_новостная_страница(final String url) {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url);

        newsPage = new NewsPage(driver);
    }

    @Когда("пользователь меняет год на {string}")
    public void пользователь_меняет_год_на(final String year) throws InterruptedException {
        newsPage.inputSelect(year);

        //new WebDriverWait(driver, Duration.ofMillis(2000));
        Thread.sleep(2000);
    }

    @Тогда("все новости должны содержать {string} в дате")
    public void все_новости_должны_содержать_в_дате(final String year) {
        assertThat(newsPage.getLastDateOfNews().getText())
                .as("Дата должна содержать указанный год")
                .endsWith(year)
                .doesNotContain("2023");

        assertThat(newsPage.getDateOfNews().getText())
                .as("Дата должна содержать указанный год")
                .endsWith(year)
                .doesNotContain("2023");
    }

    @Когда("пользователь выбирает категорию Частным клиентам")
    public void пользователь_выбирает_категорию() throws InterruptedException {
        //new WebDriverWait(driver, Duration.ofMillis(2000));
        Thread.sleep(2000);

        newsPage.clickNewsFilterValueButton();

        //new WebDriverWait(driver, Duration.ofMillis(2000));
        Thread.sleep(2000);
    }

    @Тогда("все отобразившиеся новости имеют данную категорию")
    public void все_отобразившиеся_новости_имеют_данную_категорию() {
        final Integer newsCategoryCount = newsPage.newsCategoryCount("ЧАСТНЫМ КЛИЕНТАМ");

        Assertions.assertThat(newsPage.getNewsCategoryValues())
                .filteredOn(value ->
                        value.getText().contains("ЧАСТНЫМ КЛИЕНТАМ"))
                .extracting(WebElement::getText)
                .containsOnly("ЧАСТНЫМ КЛИЕНТАМ");

        Assertions.assertThat(newsPage.getNewsList())
                .as("Кол-во новостей должно совпадать с кол-вом отображаемых категорий")
                .hasSize(newsCategoryCount);
    }

    @Тогда("закрыть новостную страницу")
    public void закрыть_новостную_страницу() {
        driver.quit();
    }
}
