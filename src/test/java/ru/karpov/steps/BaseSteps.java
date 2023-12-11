package ru.karpov.steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Затем;
import io.cucumber.java.ru.Когда;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;

public class BaseSteps extends BaseMethods {

    @Дано("перейти по url {string}")
    public void open_page(final String url)
    {
        setupDriver();
        driver.get(url);
    }

    @Затем("закрыть страницу")
    public void close_page() {
        driver.quit();
    }

    @Когда("пользователь в {string} скроллит до элемента {string}")
    public void scroll_to_element(final String className, final String elementName)
            throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {

        Class<?> clazz = Class.forName("ru.karpov.pageObjects." + className);
        Field field = clazz.getDeclaredField(elementName);
        field.setAccessible(true);
        Object pageObject = clazz.getDeclaredConstructor(WebDriver.class).newInstance(driver);
    }
}
