package ru.karpov.steps;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseSteps {
    protected static WebDriver driver;

    @Before
    public void setupDriver()
    {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
    }

    protected void screenshotAllure() {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Allure.addAttachment("screenshot", FileUtils.openInputStream(srcFile));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void input(final WebElement element, final String info) {
        final Select region = new Select(element);
        region.selectByValue(info);
    }

    protected void contentAllure(final String title, final String content)
    {
        Allure.addAttachment(title, content);
    }


}
