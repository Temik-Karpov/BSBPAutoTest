package ru.karpov.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BasePage {
    public final WebDriver driver;

    public BasePage(final WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//div[contains(@class, 'chakra-tabs__tablist')]")
    private List<WebElement> tabsListButtons;

    private WebElement select;

    public WebElement getSelect() {
        return select;
    }

    public void setSelect(WebElement select) {
        this.select = select;
    }


    public WebElement getSelect(final String option)
    {
        return driver.findElement(By.xpath("//select[.//option[.='" + option + "']]"));
    }

}
