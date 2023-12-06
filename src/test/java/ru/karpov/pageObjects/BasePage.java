package ru.karpov.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BasePage {
    public final WebDriver driver;

    public BasePage(final WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private List<WebElement> buttonList;

    public List<WebElement> getButtonList() {
        return buttonList;
    }

    public void setButtonList(List<WebElement> buttonLists) {
        this.buttonList = buttonLists;
    }
}
