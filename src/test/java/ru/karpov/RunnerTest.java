package ru.karpov;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "ru.karpov",
        tags = "@NewsTest and @YearNews",
        plugin = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
)
public class RunnerTest {
}
