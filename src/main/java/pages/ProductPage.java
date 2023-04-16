package pages;

import org.openqa.selenium.By;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import utils.MyLogger;

public class ProductPage extends MenuPage {
	private By page_hdr = AppiumBy.xpath(
			"//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView");

	@Step("Get Page header step ...")
	public String getPageHeader() {
		MyLogger.info("Get text of PageHeader");
		return getElementActions().textGet(page_hdr);
	}

	@Step("Enter product step ...")
	public void enterProduct(int index) {
		MyLogger.info("Enter product with index " + index);
		getElementActions().clickOn(AppiumBy.xpath("(//android.widget.TextView[@content-desc=\"test-Item title\"])[" + index + "]"));
	}
	
	@Step("Get product title step ...")
	public String getProductTitle(int index) {
		MyLogger.info("Get product title");
		return getElementActions().textGet(
				AppiumBy.xpath("(//android.widget.TextView[@content-desc=\"test-Item title\"])[" + index + "]"));
	}
	
	@Step("Get Expected product data step ...")
	public String getExpectedProductData(String key)
	{
		String result = productdata.getString(key);
		MyLogger.info("Get Expected product data -> " + result);
		return result;
	}

	@Step("Get product price step ...")
	public String GetProductPrice(int index) {
		MyLogger.info("Get product price");
		return getElementActions()
				.textGet(AppiumBy.xpath("(//android.widget.TextView[@content-desc=\"test-Price\"])[" + index + "]"));
	}
}
