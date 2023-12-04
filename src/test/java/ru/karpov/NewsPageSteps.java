package ru.karpov;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.karpov.pageObjects.NewsPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.Duration;
import java.util.Calendar;

public class NewsPageSteps {
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
    public void пользователь_меняет_год_на(final String year) {

        String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath(".//h3[contains(text(), " +  currentYear + ")]")));

        newsPage.inputSelect(year);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath(".//h3[contains(text(), " + year + ")]")));
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
        String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath(".//h3[contains(text(), " +  currentYear + ")]")));

        System.out.println(newsPage.newsCategoryCount("ЧАСТНЫМ КЛИЕНТАМ"));

        newsPage.clickNewsFilterValueButton();

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
