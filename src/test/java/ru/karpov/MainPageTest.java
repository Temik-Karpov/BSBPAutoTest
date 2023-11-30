package ru.karpov;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
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

    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfProperties.getProperty("mainpage"));

        jse = (JavascriptExecutor) driver;

        mainPage = new MainPage(driver);

        newsPage = new NewsPage(driver);

        currencyPage = new CurrencyPage(driver);
    }

    @Test
    public void currencyButtonTest() {
        driver.get(ConfProperties.getProperty("mainpage"));

        new WebDriverWait(driver, Duration.ofSeconds(2));

        jse.executeScript("window.scrollTo(0, 2500)");

        new WebDriverWait(driver, Duration.ofSeconds(2));

        mainPage.setCurrencyButton(new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(mainPage.getCurrencyButton())));

        mainPage.clickCurrencyButton();

        new WebDriverWait(driver, Duration.ofSeconds(2));

        assertThat(currencyPage.getCurrencyButtonList().size()).isEqualTo(5);
    }

    @Test
    public void selectRegionPetersburgTest() {
        jse.executeScript("window.scrollTo(top)");

        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(mainPage.getSelectRegion()));

        mainPage.inputSelect("Санкт-Петербург");

        final String regionPhoneText =
                driver.findElement(By.cssSelector(".css-ed0axp > #\\33 > .chakra-text")).getText();

        assertThat(regionPhoneText).isEqualTo("В Санкт-Петербурге");
    }

    @Test
    public void headerBusinessButtonTest() {
        jse.executeScript("window.scrollTo(top)");

        new WebDriverWait(driver, Duration.ofSeconds(2));

        mainPage.setBusinessButton(new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(mainPage.getBusinessButton())));

        mainPage.clickBusinessButton();

        assertThat(mainPage.getBusinessServiceText1()).isEqualTo("Услуги РКО");
        assertThat(mainPage.getBusinessServiceText2()).isEqualTo("Корпоративные карты");
        assertThat(mainPage.getBusinessServiceText3()).isEqualTo("Торговый эквайринг");
        assertThat(mainPage.getBusinessServiceText4()).isEqualTo("Мобильное приложение");
    }

    @Test
    public void showNewsButtonTest() {

        jse.executeScript("window.scrollTo(0, 2000)");

        new WebDriverWait(driver, Duration.ofSeconds(2));

        mainPage.setShowNewsButton(new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(mainPage.getShowNewsButton())));

        mainPage.clickShowNewsButton();

        new WebDriverWait(driver, Duration.ofSeconds(2));

        assertThat(newsPage.getTitleNewsText()).isEqualTo("Новости");
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}

