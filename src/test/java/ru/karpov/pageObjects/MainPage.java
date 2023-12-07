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

    @FindBy(xpath = "//a[@href = '/business']")
    private WebElement headerBusinessLink;

    @FindBy(xpath = "//a[contains(@href, '/news?client_type_id=1')]")
    private WebElement showNewsLink;

    @FindBy(css = ".css-ed0axp > #\\33 > .chakra-text")
    private WebElement regionSelect;

    @FindBy(xpath = "//a[text() = 'Перейти']")
    private WebElement currencyLink;

    @FindBy(xpath = ".//h3[text() = 'Офисы и банкоматы']")
    private WebElement mapText;

    @FindBy(xpath = ".//div[@class = 'css-1qdyvok']/div[@id = '14']")
    private WebElement footerInfo;


    public void setSelectRegion(WebElement selectRegion) {
        this.selectRegion = selectRegion;
    }

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

    public void input(final WebElement element, final String info) {
        final Select region = new Select(element);
        region.selectByValue(info);
    }

    public void inputSelect(final String regionName) {
        final Select region = new Select(this.selectRegion);
        region.selectByValue(regionName);
    }



    public WebElement getSelectRegion() {
        return selectRegion;
    }
}
