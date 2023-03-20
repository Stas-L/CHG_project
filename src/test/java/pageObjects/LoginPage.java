package pageObjects;

import cucumber.BaseTest;
import cucumber.TestUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "#user-name")
    private WebElement usernameField;

    @FindBy(css = "#password")
    private WebElement passwordField;

    @FindBy(css = "#login-button")
    private WebElement loginButton;

    @FindBy(css = ".error-message-container")
    private WebElement errorMessage;

    public void setUserLoginFromExcel(int vRow, int vColumn) {
        TestUtils.fillLoginPageFields(BaseTest.getWait(2), usernameField, vRow, vColumn);
    }

    public void setUserPasswordFromExcel(int vRow, int vColumn) {
        TestUtils.fillLoginPageFields(BaseTest.getWait(2), passwordField, vRow, vColumn);
    }

    public void clickLoginButton() {

        loginButton.click();
    }

    public String getErrorMessage() {

        return errorMessage.getAttribute("innerText");
    }
}