package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(css = ".app_logo")
    private WebElement logoIcon;

    @FindBy(css = ".title")
    private WebElement titleSauceDemoMainPage;

    public String getTitleText() {
        return titleSauceDemoMainPage.getText();
    }

    public String getLogoText() {
        return logoIcon.getAttribute("innerText");
    }
}
