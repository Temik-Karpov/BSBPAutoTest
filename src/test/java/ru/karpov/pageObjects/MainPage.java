package ru.karpov.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

@SuppressWarnings("ALL")
public class MainPage {
    public final WebDriver driver;

    public MainPage(final WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//select")
    private WebElement selectRegion;

    @FindBy(xpath = "//a[contains(@class, 'css-kixvqm')]")
    private List<WebElement> headerButtons;

    @FindBy(xpath = "//*[contains(@class, 'css-pka4it')]")
    private WebElement showNewsButton;

    @FindBy(css = ".css-ed0axp > #\\33 > .chakra-text")
    private WebElement regionSelect;

    @FindBy(xpath = ".//a[@href = '/finance/exchange'][@class = 'chakra-link css-6x1e5l']")
    private WebElement currencyButton;

    @FindBy(xpath = ".//h3[text() = 'Офисы и банкоматы']")
    private WebElement mapText;

    @FindBy(xpath = ".//div[@class = 'css-1qdyvok']/div[@id = '14']")
    private WebElement footerInfo;

    public List<WebElement> getHeaderButtons() {
        return headerButtons;
    }

    public String getRegionSelectText() {
        return regionSelect.getText();
    }

    public WebElement getFooterInfo() {
        return footerInfo;
    }

    public WebElement getMapText() {
        return mapText;
    }

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


    public void inputSelect(final String regionName) {
        final Select region = new Select(this.selectRegion);
        region.selectByValue(regionName);
    }

    public void clickShowNewsButton()
    {
        this.showNewsButton.click();
    }


    public WebElement getSelectRegion() {
        return selectRegion;
    }


    public WebElement getShowNewsButton() {
        return showNewsButton;
    }


    public void setShowNewsButton(final WebElement showNewsButton) {
        this.showNewsButton = showNewsButton;
    }
}
