package ru.karpov;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.karpov.pageObjects.BusinessPage;
import ru.karpov.pageObjects.CurrencyPage;
import ru.karpov.pageObjects.MainPage;
import ru.karpov.pageObjects.NewsPage;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MainPageSteps extends BaseSteps {

    public static MainPage mainPage;
    public static NewsPage newsPage;
    public static CurrencyPage currencyPage;

    public static BusinessPage businessPage;
    public static JavascriptExecutor jse;

    @Дано("открытая главная страница {string}")
    public static void open_page(final String url) {
        setupDriver();
        driver.get(url);

        jse = (JavascriptExecutor) driver;

        mainPage = new MainPage(driver);

        newsPage = new NewsPage(driver);

        currencyPage = new CurrencyPage(driver);

        businessPage = new BusinessPage(driver);
    }

    @Когда("пользователь нажимает на кнопку Перейти")
    public void пользователь_нажимает_на_кнопку_перейти() {

        jse.executeScript("window.scrollTo(0, 2400)");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath(".//ymaps[@class ='ymaps-2-1-79-events-pane ymaps-2-1-79-user-selection-none']")));

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(mainPage.getCurrencyButton()));

        mainPage.clickCurrencyButton();

        new WebDriverWait(driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.visibilityOf(currencyPage.getCurrencyTitle()));
    }

    @Тогда("должны появиться пункты:")
    public void должны_появиться_пункты(Map<String, Integer> buttons) {
        Assertions.assertThat(currencyPage.getCurrencyButtonList())
                .as("Кол-во кнопок на странице валюты должен быть 5")
                .hasSize(buttons.size());
    }

    @Когда("пользователь меняет региона на {string}")
    public void пользователь_меняет_региона_на(final String region) {

        new WebDriverWait(driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.elementToBeClickable(mainPage.getSelectRegion()));

        mainPage.inputSelect(region);
    }

    @Тогда("текст под номером телефона меняется на {string}")
    public void текст_под_номером_телефона_меняется_на(final String textUnderPhoneNumber) {
        final String regionPhoneText = mainPage.getRegionSelectText();

        assertThat(regionPhoneText)
                .as("Под номером телефона должна отображаться надпись региона")
                .isNotNull()
                .isEqualTo(textUnderPhoneNumber);
    }

    @Когда("пользователь нажимает на кнопку Бизнесу")
    public void пользователь_нажимает_на_кнопку_бизнесу() {
        mainPage.setBusinessButton(new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(mainPage.getBusinessButton())));

        mainPage.clickBusinessButton();
    }

    @Когда("пользователь нажимает на кнопку Новостей")
    public void пользователь_нажимает_на_кнопку_новостей() {

        jse.executeScript("window.scrollTo(0, 2300)");

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        (By.xpath(".//div[@class = 'css-e1b41g']"))));


        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(mainPage.getShowNewsButton()));

        mainPage.clickShowNewsButton();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(newsPage.getLastDateOfNews()));
    }

    @Тогда("появляется заглавный текст {string}")
    public void появляется_заглавный_текст(final String title) {
        assertThat(newsPage.getTitleNewsText())
                .as("Заглавным текстом на данной странице должен быть 'Новости'")
                .isNotNull()
                .isEqualTo(title);
    }



    @Тогда("появляются нужные пункты:")
    public void появляются_нужные_пункты(List<String> dataTable) {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(businessPage.getBusinessServiceText1())
                .as("Первая кнопка должна иметь текст 'Услуги РКО'")
                .isEqualTo(dataTable.get(0));

        softly.assertThat(businessPage.getBusinessServiceText2())
                .as("Вторая кнопка должна иметь текст 'Бизнес карта'")
                .isEqualTo(dataTable.get(1));

        softly.assertThat(businessPage.getBusinessServiceText3())
                .as("Третья кнопка должна иметь текст 'Торговый эквайринг'")
                .isEqualTo(dataTable.get(2));

        softly.assertAll();
    }

    @Тогда("закрыть главную страницу")
    public void close_page() {
        driver.quit();
    }
}

