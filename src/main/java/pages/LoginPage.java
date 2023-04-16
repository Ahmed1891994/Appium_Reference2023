package pages;

import org.openqa.selenium.By;
import base.Base;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import utils.MyLogger;
import utils.TestDataGenerator;

public class LoginPage extends Base {
	private By username_fld = AppiumBy.accessibilityId("test-Username");
	private By password_fld = AppiumBy.accessibilityId("test-Password");
	private By login_btn = AppiumBy.accessibilityId("test-LOGIN");
	private By error_txt = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");

	@Step("set Username with value {0} step ...")
	public void EnterUsername(String username) {
		MyLogger.info("set username -> " + username + " in field");
		getElementActions().textSet(username_fld, username);
	}

	@Step("set Password with value {0} step ...")
	public void EnterPassword(String password) {
		MyLogger.info("set password -> " + password + " in field");
		getElementActions().textSet(password_fld, password);
	}

	@Step("Press Login Button step ...")
	public void pressLogin() {
		String firstname = TestDataGenerator.fakerDataUser.getFirstname();
		String lastname = TestDataGenerator.fakerDataUser.getLastname();
		String password = TestDataGenerator.fakerDataUser.getPassword();
		MyLogger.info("firstname generated from Faker library -> " + firstname
				+ " ,and lastname generated from faker library -> " + lastname);
		EnterUsername(firstname + lastname);
		MyLogger.info("username Entered is -> " + firstname + lastname);
		MyLogger.info("password generated from Faker library -> " + password);
		MyLogger.info("Enter Password into Field");
		EnterPassword(password);
		MyLogger.info("Click on login button");
		getElementActions().clickOn(login_btn);
	}

	@Step("Pass username, Password then Press Login Button step ...")
	public void pressLogin(String key) {
		String username = logindata.getJSONObject(key).getString("username");
		String password = logindata.getJSONObject(key).getString("password");
		EnterUsername(username);
		MyLogger.info("username Entered is -> " + username);
		EnterPassword(password);
		MyLogger.info("password Entered is -> " + password);
		MyLogger.info("Click on login button");
		getElementActions().clickOn(login_btn);
	}

	@Step("Get Error Text step ...")
	public String getPageErrorText() {
		String result = getElementActions().textGet(error_txt);
		MyLogger.info("Get Error Text text -> " + result);
		return result;
	}

	@Step("Get Expected Error Text step ...")
	public String getExpectedErrorText(String key)
	{
		String result = errordata.getString(key);
		MyLogger.info("Get Expected Error Text text -> " + result);
		return result;
	}
}
