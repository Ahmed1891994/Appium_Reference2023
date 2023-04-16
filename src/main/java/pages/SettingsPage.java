package pages;

import org.openqa.selenium.By;
import base.Base;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

public class SettingsPage extends Base{
	private By logout_btn = AppiumBy.accessibilityId("test-LOGOUT");
	
	@Step("Press Logout button step ...")
	public void pressLogoutBtn() {
		getElementActions().clickOn(logout_btn);
	}
}
