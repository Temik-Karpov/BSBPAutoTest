package ru.karpov.pageObjects;

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

    @FindBy(xpath = "//*[contains(@class, 'css-17igell')]")
    private WebElement selectRegion;


    @FindBy(xpath = "//*[contains(@class, 'css-1hxq3ev')]")
    private WebElement button;


    @FindBy(xpath = "//a[@href = '/business']")
    private WebElement businessButton;


    @FindBy(xpath = "//*[contains(@class, 'css-pka4it')]")
    private WebElement showNewsButton;


    @FindBy(xpath = "//*[contains(@id, 'tabs-6--tab-0')]")
    private WebElement businessService1;


    @FindBy(xpath = "//*[contains(@id, 'tabs-6--tab-1')]")
    private WebElement businessService2;


    @FindBy(xpath = "//*[contains(@id, 'tabs-6--tab-2')]")
    private WebElement businessService3;

    @FindBy(css = ".css-ed0axp > #\\33 > .chakra-text")
    private WebElement regionSelect;

    public String getRegionSelectText() {
        return regionSelect.getText();
    }

    @FindBy(xpath = ".//a[@href = '/finance/exchange'][@class = 'chakra-link css-6x1e5l']")
    private WebElement currencyButton;

    public void setCurrencyButton(final WebElement currencyButton) {
        this.currencyButton = currencyButton;
    }

    public void clickCurrencyButton()
    {
        this.currencyButton.click();
    }

    public WebElement getCurrencyButton() {
        return currencyButton;
    }

    public String getBusinessServiceText1() {
        return this.businessService1.getText();
    }

    public String getBusinessServiceText2() {
        return this.businessService2.getText();
    }

    public String getBusinessServiceText3() {
        return this.businessService3.getText();
    }

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


    public WebElement getSelectRegion() {
        return selectRegion;
    }


    public WebElement getBusinessButton() {
        return businessButton;
    }

    public WebElement getShowNewsButton() {
        return showNewsButton;
    }

    public void setBusinessButton(final WebElement businessButton) {
        this.businessButton = businessButton;
    }

    public void setShowNewsButton(final WebElement showNewsButton) {
        this.showNewsButton = showNewsButton;
    }
}
