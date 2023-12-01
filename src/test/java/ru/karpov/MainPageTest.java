package ru.karpov;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.AfterClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.karpov.pageObjects.CurrencyPage;
import ru.karpov.pageObjects.MainPage;
import ru.karpov.pageObjects.NewsPage;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MainPageTest {

    public static MainPage mainPage;
    public static NewsPage newsPage;
    public static CurrencyPage currencyPage;
    public static WebDriver driver;
    public static JavascriptExecutor jse;

    @Дано("открытая главная страница {string}")
    public static void setup(final String url) {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(url);

        jse = (JavascriptExecutor) driver;

        mainPage = new MainPage(driver);

        newsPage = new NewsPage(driver);

        currencyPage = new CurrencyPage(driver);
    }

    @Когда("пользователь нажимает на кнопку Перейти")
    public void пользователь_нажимает_на_кнопку_перейти() throws InterruptedException {
        //new WebDriverWait(driver, Duration.ofSeconds(2));
        Thread.sleep(2000);

        jse.executeScript("window.scrollTo(0, 2500)");

        //new WebDriverWait(driver, Duration.ofSeconds(2));
        Thread.sleep(2000);

        mainPage.setCurrencyButton(new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(mainPage.getCurrencyButton())));

        mainPage.clickCurrencyButton();

        //new WebDriverWait(driver, Duration.ofSeconds(2));
        Thread.sleep(2000);
    }

    @Тогда("должно появиться {int} пунктов")
    public void должно_появиться_пунктов(final Integer buttonCount) {
        Assertions.assertThat(currencyPage.getCurrencyButtonList())
                .as("Кол-во кнопок на странице валюты должен быть 5")
                .hasSize(buttonCount);
    }

    @Когда("пользователь меняет региона на {string}")
    public void пользователь_меняет_региона_на(final String region) {

        new WebDriverWait(driver, Duration.ofSeconds(10)).
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

    @Тогда("появляются нужные пункты")
    public void появляются_услуги_рко_торговый_эквайринг_мобильное_приложение() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(mainPage.getBusinessServiceText1())
                .as("Первая кнопка должна иметь текст 'Услуги РКО'")
                .isEqualTo("Услуги РКО");

        softly.assertThat(mainPage.getBusinessServiceText2())
                .as("Вторая кнопка должна иметь текст 'Бизнес карта'")
                .isEqualTo("Бизнес карта");

        softly.assertThat(mainPage.getBusinessServiceText3())
                .as("Третья кнопка должна иметь текст 'Торговый эквайринг'")
                .isEqualTo("Торговый эквайринг");

        softly.assertAll();
    }

    @Когда("пользователь нажимает на кнопку Новостей")
    public void пользователь_нажимает_на_кнопку_новостей() throws InterruptedException {
        jse.executeScript("window.scrollTo(0, 2000)");

        //new WebDriverWait(driver, Duration.ofSeconds(2));
        Thread.sleep(2000);

        mainPage.clickShowNewsButton();

        //new WebDriverWait(driver, Duration.ofSeconds(2));
        Thread.sleep(2000);
    }

    @Тогда("появляется заглавный текст {string}")
    public void появляется_заглавный_текст(final String title) {
        assertThat(newsPage.getTitleNewsText())
                .as("Заглавным текстом на данной странице должен быть 'Новости'")
                .isNotNull()
                .isEqualTo(title);
    }

    @Тогда("закрыть главную страницу")
    public void закрыть_страницу() {
        driver.quit();
    }
    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}

