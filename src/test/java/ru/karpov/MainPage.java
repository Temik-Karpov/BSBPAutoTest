package ru.karpov;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class MainPage {
    public final WebDriver driver;

    public MainPage(final WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[contains(@class, 'chakra-select css-17igell')]")
    private WebElement selectRegion;

    @FindBy(xpath = "//*[contains(@class, 'chakra-link css-1hxq3ev')]")
    private WebElement button;

    @FindBy(css = ".css-kixvqm:nth-child(2)")
    private WebElement businessButton;

    @FindBy(xpath = "//*[contains(@class, 'chakra-link css-pka4it')]")
    private WebElement showNewsButton;

    public void inputSelect(final String regionName) {
        final Select region = new Select(this.selectRegion);
        region.selectByValue(regionName);
    }

    public void clickBusinessButton()
    {
        this.businessButton.click();
    }

    public void clickShowNewsButton()
    {
        this.showNewsButton.click();
    }
}
