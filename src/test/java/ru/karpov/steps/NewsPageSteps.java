package ru.karpov.steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.*;
import ru.karpov.pageObjects.NewsPage;

import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class NewsPageSteps extends BaseSteps {

    public final NewsPage newsPage = new NewsPage(driver);

    @Тогда("все новости должны содержать {string} в дате")
    public void все_новости_должны_содержать_в_дате(final String year) {

        assertThat(newsPage.getDateOfNews().getText())
                .as("Дата должна содержать указанный год")
                .endsWith(year)
                .doesNotContain("2023");
    }

    @Когда("пользователь выбирает {string}")
    public void пользователь_выбирает_категорию(final String buttonText) {
        newsPage.getNewsFilterValues()
                .stream()
                .filter(value -> value.getText().equals(buttonText))
                .collect(Collectors.toList())
                .get(0)
                .click();
    }

    @Тогда("все отобразившиеся новости имеют {string}")
    public void все_отобразившиеся_новости_имеют_данную_категорию(final String buttonText) {
        Assertions.assertThat(newsPage.getNewsCategoryValues())
                .extracting(WebElement::getText)
                .contains(buttonText);

    }



}
