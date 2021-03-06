package org.fundacionjala.salesforce.cucumber.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.fundacionjala.core.selenium.interaction.WebDriverManager;
import org.testng.SkipException;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * [SL] This class contains hooks for the project.
 */
public class Hooks {

    /**
     * Takes a screenshot and attach to scenario if this scenario failed.
     *
     * @param scenario
     */
    @After
    public void after(final Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            WebDriver driver = WebDriverManager.getInstance().getWebDriver();
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(src);
            scenario.attach(fileContent, "image/png", new Date().getTime() + "");
        }
    }

    /**
     * Skips a test scenario.
     */
    @Before(value = "@skipScenario", order = 0)
    public void skipTestScenario() {
        throw new SkipException("Test Skipped");
    }
}
