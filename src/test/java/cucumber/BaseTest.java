package cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.BasePage;

import java.time.Duration;

public class BaseTest extends BasePage {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @Before
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            TestUtils.captureScreenShot(getDriver(), scenario.getName(), "TestRunner");
        }

        driver.quit();
        wait = null;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getWait(int seconds) {
        if (wait == null) {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(seconds));
        }
        return wait;
    }
}