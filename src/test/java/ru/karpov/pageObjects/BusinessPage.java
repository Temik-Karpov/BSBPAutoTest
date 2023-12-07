package ru.karpov.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BusinessPage {

    public final WebDriver driver;

    public BusinessPage(final WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//button[@id = 'tabs-6--tab-0']")
    private WebElement tabFirstButton;

    @FindBy(xpath = "//button[@id = 'tabs-6--tab-1']")
    private WebElement tabSecondButton;

    @FindBy(xpath = "//button[@id = 'tabs-6--tab-2']")
    private WebElement tabThirdButton;

    @FindBy(xpath = "//button[@id = 'tabs-6--tab-3']")
    private WebElement tabFourthButton;
}
