package ru.karpov.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CurrencyPage {

    public final WebDriver driver;

    public CurrencyPage(final WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//button[@role = 'tab'][@data-index = '0']")
    private WebElement tabFirstButton;

    @FindBy(xpath = "//button[@role = 'tab'][@data-index = '1']")
    private WebElement tabSecondButton;

    @FindBy(xpath = "//button[@role = 'tab'][@data-index = '2']")
    private WebElement tabThirdButton;

    @FindBy(xpath = "//button[@role = 'tab'][@data-index = '3']")
    private WebElement tabFourthButton;

    @FindBy(xpath = "//button[@role = 'tab'][@data-index = '4']")
    private WebElement tabFifthButton;
}
