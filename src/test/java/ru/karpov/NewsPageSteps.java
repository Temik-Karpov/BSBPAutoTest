package ru.karpov;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.karpov.pageObjects.NewsPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.Duration;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;


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


        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath(".//h3[contains(text(), " + year + ")]")));

        screenshotAllure();
    }

    @Тогда("все новости должны содержать {string} в дате")
    public void все_новости_должны_содержать_в_дате(final String year) {


        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(newsPage.getLastDateOfNews().getText())
                .as("Дата должна содержать указанный год")
                .endsWith(year)
                .doesNotContain("2023");

        softly.assertThat(newsPage.getDateOfNews().getText())
                .as("Дата должна содержать указанный год")
                .endsWith(year)
                .doesNotContain("2023");

        softly.assertAll();
    }

    @Когда("пользователь выбирает {string}")
    public void пользователь_выбирает_категорию(final String buttonText) {
        List<WebElement> list = newsPage.getNewsFilterValues()
                .stream()
                .filter(value -> value.getText().equals(buttonText)).collect(Collectors.toList());

        list.get(0).click();
    }

    @Тогда("все отобразившиеся новости имеют {string}")
    public void все_отобразившиеся_новости_имеют_данную_категорию(final String buttonText) {

        Assertions.assertThat(newsPage.getNewsCategoryValues())
                .extracting(WebElement::getText)
                .contains(buttonText);

    }



}
