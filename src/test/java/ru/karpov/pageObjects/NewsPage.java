package ru.karpov.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class NewsPage {

    public NewsPage(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//*[@id=\"app-wrapper\"]/main/div/div/div[1]/h2")
    private WebElement titleNews;

    @FindBy(xpath="//div[contains(@class, 'css-1uifjdt')]/select")
    private WebElement selectYear;

    @FindBy(xpath = ".//div[contains(@class, 'css-11s8iwf')][contains(@class, 'chakra-container')]" +
            "/h3[@class = 'css-1elu3ke'][position()=1]")
    private WebElement lastDateOfNews;

    @FindBy(xpath = ".//div[@class = 'css-70qvj9']/p[@class = 'chakra-text css-1odxvob']")
    private WebElement dateOfNews;

    @FindBy(xpath = ".//div[@class = 'css-2imjyh']/span[@class='css-sid0p6']")
    private List<WebElement> newsCategoryValues;
    ////div[contains(@class, 'chakra-container')][.//button[contains(@class, 'css-2vve5b')][text()='Бизнесу']]/div[contains(@class, 'chakra-linkbox')]//span

    @FindBy(xpath = "//div[@class = 'css-2imjyh']/button")
    private List<WebElement> newsFilterValues;

    @FindBy(xpath = ".//div[@class = 'chakra-linkbox css-ijriu2']")
    private List<WebElement> newsList;

    public List<WebElement> getNewsFilterValues() {
        return newsFilterValues;
    }

    public List<WebElement> getNewsCategoryValues() {
        return newsCategoryValues;
    }

    public List<WebElement> getNewsList() {
        return newsList;
    }

    public Integer newsCategoryCount(final String category)
    {
        int count = 0;
        for(WebElement element : this.newsCategoryValues)
        {
            if(element.getText().equals(category))
                count++;
        }
        return count;
    }

    public WebElement getDateOfNews() {
        return dateOfNews;
    }

    public WebElement getLastDateOfNews() {
        return lastDateOfNews;
    }


    public void inputSelect(final String year) {
        final Select region = new Select(this.selectYear);
        region.selectByValue(year);
    }

    public String getTitleNewsText() {
        return titleNews.getText();
    }

}
