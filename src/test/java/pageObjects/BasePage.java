package pageObjects;

import cucumber.BaseTest;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public BasePage() {
        PageFactory.initElements(BaseTest.getDriver(), this);
    }
}
