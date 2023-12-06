package ru.karpov.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class NewsPage {

    public NewsPage(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//*[@id=\"app-wrapper\"]/main/div/div/div[1]/h2")
    private WebElement titleNews;

    @FindBy(xpath = ".//div[contains(@class, 'css-11s8iwf')][contains(@class, 'chakra-container')]" +
            "/h3[@class = 'css-1elu3ke'][position()=1]")
    private WebElement lastDateOfNews;

    @FindBy(xpath = ".//div[@class = 'css-70qvj9']/p[@class = 'chakra-text css-1odxvob']")
    private WebElement dateOfNews;

    @FindBy(xpath = ".//div[@class = 'css-2imjyh']/span[@class='css-sid0p6']")
    private List<WebElement> newsCategoryValues;

    @FindBy(xpath = "//div[contains(@class, 'chakra-container')][.//button='Бизнесу']//button")
    private List<WebElement> newsFilterValues;

    public List<WebElement> getNewsFilterValues() {
        return newsFilterValues;
    }

    public List<WebElement> getNewsCategoryValues() {
        return newsCategoryValues;
    }

    public WebElement getDateOfNews() {
        return dateOfNews;
    }

    public WebElement getLastDateOfNews() {
        return lastDateOfNews;
    }

    public String getTitleNewsText() {
        return titleNews.getText();
    }

}
