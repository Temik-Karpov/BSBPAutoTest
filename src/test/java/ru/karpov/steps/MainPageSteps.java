package ru.karpov.steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.karpov.pageObjects.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.setRemoveAssertJRelatedElementsFromStackTrace;

public class MainPageSteps extends BaseMethods {

    public final MainPage mainPage = new MainPage(driver);
    public final JavascriptExecutor jse = (JavascriptExecutor) driver;

    public final BasePage basePage = new BasePage(driver);


    @Тогда("в {string} должны появиться кнопки:")
    public void должны_появиться_пункты(final String className, Map<String,String> buttons)
            throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        Class<?> clazz = Class.forName("ru.karpov.pageObjects." + className);

        List<WebElement> buttonList = new ArrayList<>();
        for(String key : buttons.keySet())
        {
            Field field = clazz.getDeclaredField(key);
            field.setAccessible(true);
            Object pageObject = clazz.getDeclaredConstructor(WebDriver.class).newInstance(driver);
            buttonList.add((WebElement) field.get(pageObject));
        }

        jse.executeScript("window.scrollTo(0, 200)");

        Assertions.assertThat(buttonList)
                .extracting(WebElement::getText)
                .isEqualTo(new ArrayList<>(buttons.values()))
                .hasSize(buttons.size());
    }

    @Когда("пользователь меняет select на {string}")
    public void пользовательМеняетНа(final String text) {
        basePage.setSelect(new WebDriverWait(driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.elementToBeClickable(basePage.getSelect(text))));

        input(basePage.getSelect(), text);
        screenshotAllure();
        contentAllure("content", text);
    }

    @Тогда("текст под номером телефона меняется на {string}")
    public void текст_под_номером_телефона_меняется_на(final String textUnderPhoneNumber) {
        final String regionPhoneText = mainPage.getRegionSelectText();

        assertThat(regionPhoneText)
                .as("Под номером телефона должна отображаться надпись региона")
                .isNotNull()
                .isEqualTo(textUnderPhoneNumber);
    }



    @Когда("пользователь нажимает на {string} в header")
    public void user_press_button(final String objectName) throws NoSuchFieldException, IllegalAccessException {

        Class<?> c = mainPage.getClass();
        Field field = c.getDeclaredField(objectName);
        field.setAccessible(true);
        WebElement link = (WebElement) field.get(mainPage);

        link.click();
    }

    @Когда("пользователь нажимает на {string}")
    public void пользовательНажимаетНаКнопку(final String objectName) throws NoSuchFieldException, IllegalAccessException, InterruptedException {

        Class<?> c = mainPage.getClass();
        Field field = c.getDeclaredField(objectName);
        field.setAccessible(true);
        WebElement link = (WebElement) field.get(mainPage);

        jse.executeScript("window.scrollTo(0, 2200)");
        link.sendKeys("test");
        link.click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOf(link));
    }
}

