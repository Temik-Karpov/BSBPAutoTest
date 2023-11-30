package ru.karpov;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class MainPageTest {

    public static MainPage mainPage;
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("mainpage"));

        mainPage = new MainPage(driver);

    }

    @Test
    public void selectRegionPetersburgTest() throws InterruptedException {
        Thread.sleep(5000);

        mainPage.inputSelect("Санкт-Петербург");

        final String regionPhoneText =
                driver.findElement(By.cssSelector(".css-ed0axp > #\\33 > .chakra-text")).getText();

        Assert.assertEquals("В Санкт-Петербурге", regionPhoneText);
    }

    @Test
    public void headerBusinessButtonTest() {

        mainPage.clickBusinessButton();
        Assert.assertEquals("Услуги РКО",driver.findElement(By.id("tabs-6--tab-0")).getText());
        Assert.assertEquals("Корпоративные карты", driver.findElement(By.id("tabs-6--tab-1")).getText());
        Assert.assertEquals("Торговый эквайринг", driver.findElement(By.id("tabs-6--tab-2")).getText());
        Assert.assertEquals("Мобильное приложение", driver.findElement(By.id("tabs-6--tab-3")).getText());
    }

    @Test
    public void showNewsButtonTest() {

        mainPage.clickShowNewsButton();

        Assert.assertEquals(ConfProperties.getProperty("newsUrl"),driver.getCurrentUrl());

    }

    @Test
    public void supportButtonTest()
    {
        driver.findElement(By.cssSelector(".css-82oyxj")).click();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}

