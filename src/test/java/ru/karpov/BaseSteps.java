package ru.karpov;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseSteps {
    protected static WebDriver driver;

    protected static void setupDriver()
    {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    protected static void screenshotAllure() {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Allure.addAttachment("screenshot", FileUtils.openInputStream(srcFile));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    protected static void contentAllure(final String title, final String content)
    {
        Allure.addAttachment(title, content);
    }
}
