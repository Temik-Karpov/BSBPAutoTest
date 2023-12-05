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

    @FindBy(xpath = "//*[contains(@id, 'tabs-6--tab-0')]")
    private WebElement businessService1;


    @FindBy(xpath = "//*[contains(@id, 'tabs-6--tab-1')]")
    private WebElement businessService2;


    @FindBy(xpath = "//*[contains(@id, 'tabs-6--tab-2')]")
    private WebElement businessService3;

    public String getBusinessServiceText1() {
        return
                businessService1.getText();
    }

    public String getBusinessServiceText2() {
        return this.businessService2.getText();
    }

    public String getBusinessServiceText3() {
        return this.businessService3.getText();
    }
}
