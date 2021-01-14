package org.fundacionjala.salesforce.cucumber.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.fundacionjala.core.selenium.interaction.WebDriverManager;

import java.util.Date;

/**
 * This class contains hooks for the project.
 */
public class Hooks {
    /**
     * Takes a screenshot and attach to scenario if this scenario failed.
     * @param scenario
     */
    @After
    public void after(final Scenario scenario) {
        if (scenario.isFailed()) {
            WebDriver driver = WebDriverManager.getInstance().getWebDriver();
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", new Date().getTime() + "");
        }
    }
}
