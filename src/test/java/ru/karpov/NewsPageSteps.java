package ru.karpov;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.karpov.pageObjects.NewsPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.Duration;
import java.util.Calendar;


public class NewsPageSteps extends BaseSteps {

    public NewsPage newsPage = new NewsPage(driver);

    @Дано("открытая новостная страница {string}")
    public void open_page(final String url) {
        setupDriver();
        driver.get(url);
    }

    @Когда("пользователь меняет {string}")
    public void пользователь_меняет_год_на(final String year) {

        String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath(".//h3[contains(text(), " +  currentYear + ")]")));


        newsPage.inputSelect(year);


        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath(".//h3[contains(text(), " + year + ")]")));

        screenshotAllure();
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

    @Когда("пользователь выбирает {string}")
    public void пользователь_выбирает_категорию(final String buttonText) throws InterruptedException {
        System.out.println(newsPage.getNewsFilterValues().size());
        for(WebElement w : newsPage.getNewsFilterValues())
        {
            System.out.println(w.getText());
            if(w.getText().equals(buttonText))
            {
                w.click();
                break;
            }
        }

        Thread.sleep(2000);
    }

    @Тогда("все отобразившиеся новости имеют {string}")
    public void все_отобразившиеся_новости_имеют_данную_категорию(final String buttonText) {
        final Integer newsCategoryCount = newsPage.newsCategoryCount(buttonText);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(newsPage.getNewsCategoryValues())
                .filteredOn(value ->
                        value.getText().toLowerCase().contains(buttonText))
                .extracting(WebElement::getText)
                .containsOnly(buttonText);

        softly.assertThat(newsPage.getNewsList())
                .as("Кол-во новостей должно совпадать с кол-вом отображаемых категорий")
                .hasSize(newsCategoryCount);

        softly.assertAll();
    }



}
