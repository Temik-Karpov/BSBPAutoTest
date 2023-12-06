package ru.karpov;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.karpov.pageObjects.*;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MainPageSteps extends BaseSteps {

    public MainPage mainPage = new MainPage(driver);
    public NewsPage newsPage = new NewsPage(driver);
    public JavascriptExecutor jse = (JavascriptExecutor) driver;

    public BasePage basePage = new BasePage(driver);


    @Тогда("в {string} должны появиться кнопки:")
    public void должны_появиться_пункты(final String block, List<String> buttons) {
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//div[contains(@class, 'chakra-tabs__tablist')][.//button = '"
                                + buttons.get(0) +"']")));

        basePage.setButtonList(driver.findElements
                (By.xpath("//div[contains(@class, '"+ block + "')]/button")));

        for(WebElement el : basePage.getButtonList())
        {
            System.out.println(el.getText());
        }

        Assertions.assertThat(basePage.getButtonList())
                        .extracting(WebElement::getText)
                        .isEqualTo(buttons);

        Assertions.assertThat(basePage.getButtonList())
                .as("Кол-во кнопок должно совпадать с кол-вом аргументов")
                .hasSize(buttons.size());
    }

    @Когда("пользователь меняет select на {string}")
    public void пользовательМеняетНа(final String text) {
        mainPage.setSelectRegion(new WebDriverWait(driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//select[.//option[.='" + text + "']]"))));

        mainPage.input(mainPage.getSelectRegion(), text);
    }

    @Тогда("текст под номером телефона меняется на {string}")
    public void текст_под_номером_телефона_меняется_на(final String textUnderPhoneNumber) {
        final String regionPhoneText = mainPage.getRegionSelectText();

        assertThat(regionPhoneText)
                .as("Под номером телефона должна отображаться надпись региона")
                .isNotNull()
                .isEqualTo(textUnderPhoneNumber);
    }

    @Тогда("появляется текст {string}")
    public void появляется_заглавный_текст(final String title) {
        assertThat(newsPage.getTitleNewsText())
                .as("Заглавным текстом на данной странице должен быть 'Новости'")
                .isNotNull()
                .isEqualTo(title);
    }

    @Когда("пользователь нажимает на {string} в header")
    public void user_press_button(final String buttonText) {
        for(WebElement w : mainPage.getHeaderButtons()) {
            if (w.getText().equals(buttonText)) {
                w.click();
                break;
            }
        }
    }

    @Когда("пользователь нажимает на кнопку {string}")
    public void пользовательНажимаетНаКнопку(final String buttonText) {
        mainPage.setShowNewsButton(driver.findElement(By.xpath("//a[text() = '" + buttonText + "']")));

        jse.executeScript("window.scrollTo(0, 2100)");

        new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.invisibilityOf(newsPage.getLastDateOfNews()));

        mainPage.getShowNewsButton().click();

        new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.invisibilityOf(mainPage.getShowNewsButton()));
    }
}

