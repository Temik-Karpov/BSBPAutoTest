package ru.karpov.pageObjects;

import org.openqa.selenium.By;
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

    private WebElement select;

    public WebElement getSelect() {
        return select;
    }

    public void setSelect(WebElement select) {
        this.select = select;
    }

    public List<WebElement> getButtonList() {
        return buttonList;
    }

    public void setButtonList(final String block) {
        this.buttonList = driver.findElements
                (By.xpath("//div[contains(@class, '"+ block + "')]/button"));
    }

    public WebElement getBlockButton(final String block, final String button)
    {
       return driver.findElement(By.xpath("//div[contains(@class, '" + block + "')][.//button = '"
                + button +"']"));
    }

    public WebElement getSelect(final String option)
    {
        return driver.findElement(By.xpath("//select[.//option[.='" + option + "']]"));
    }

    public WebElement getLink(final String text)
    {
        return driver.findElement(By.xpath("//a[text() = '" + text + "']"));
    }
}
