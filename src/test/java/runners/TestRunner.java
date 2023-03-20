package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

        features = "src/test/resources/features/login.feature",
        glue = {"cucumber", "pageObjects", "runners"},
        monochrome = true,
        plugin = {"pretty", "html:target/HtmlReportsCucumber/report.html",
                "pretty", "json:target/JsonReportsCucumber/report.json",
                "pretty", "junit:target/JUnitReportsCucumber/report.xml"}
)

public class TestRunner extends AbstractTestNGCucumberTests {

}