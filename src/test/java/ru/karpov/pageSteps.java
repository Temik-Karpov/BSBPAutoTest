package ru.karpov;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Затем;

public class pageSteps extends BaseSteps {

    @Дано("перейти по url {string}")
    public void open_page(final String url)
    {
        setupDriver();
        driver.get(url);
    }

    @Затем("закрыть страницу")
    public void close_page() {
        driver.quit();
    }
}
