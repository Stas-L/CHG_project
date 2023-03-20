package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageObjects.LoginPage;
import pageObjects.MainPage;

public class LoginSteps {

    private final MainPage mainPage = new MainPage();
    private final LoginPage loginPage = new LoginPage();

    @Given("I am on the Sauce Demo Login Page")
    public void getUrl() {
        BaseTest.getDriver().get("https://www.saucedemo.com/");
    }

    @When("I fill the account information for account StandardUser into the Username field and the Password field")
    public void fillStandardUserLoginAndPasswordFields() {
        loginPage.setUserLoginFromExcel(1, 1);
        loginPage.setUserPasswordFromExcel(1, 2);
    }

    @When("I fill the account information for account LockedOutUser into the Username field and the Password field")
    public void fillLockedOutUserLoginAndPasswordFields() {
        loginPage.setUserLoginFromExcel(2, 1);
        loginPage.setUserPasswordFromExcel(2, 2);
    }

    @And("I click the Login Button")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }

    @And("I am redirected to the Sauce Demo Main Page")
    public void redirectToMainPage() {
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualURL = BaseTest.getDriver().getCurrentUrl();

        Assert.assertEquals(actualURL, expectedUrl);
        Assert.assertEquals(mainPage.getTitleText(), "Products");
    }

    @Then("I verify the App Logo exists")
    public void isLogoExists() {
        String actualLogoText = mainPage.getLogoText();

        Assert.assertFalse(actualLogoText.isEmpty());
        Assert.assertEquals(actualLogoText, "Swag Labs");
    }

    @Then("I verify the Error Message contains the text {string}")
    public void verifyErrorMessage(String expectedResult) {
        String actualResult = loginPage.getErrorMessage();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
