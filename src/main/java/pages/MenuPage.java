package pages;

import org.openqa.selenium.By;

import base.Base;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import utils.MyLogger;

public class MenuPage extends Base{
	private By settings_btn = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView");
	
	@Step("Enter Settings in menu step ...")
	public void pressSettingsBtn() {
		MyLogger.info("Press Settings button");
		getElementActions().clickOn(settings_btn);
	}
}
