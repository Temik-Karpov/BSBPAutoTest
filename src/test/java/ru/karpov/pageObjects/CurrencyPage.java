package ru.karpov.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CurrencyPage {
    private final WebDriver driver;

    public CurrencyPage(final WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = ".//div[@class = 'chakra-tabs__tablist css-ffvuvs']/button")
    private List<WebElement> currencyButtonList;

    public List<WebElement> getCurrencyButtonList() {
        return currencyButtonList;
    }
}