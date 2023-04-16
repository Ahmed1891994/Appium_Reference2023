package pages;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import utils.MyLogger;

public class ProductDetailsPage extends MenuPage{
	private By product_title_lbl = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]");
	private By product_description_txt = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[2]");
	private By back_to_product_btn = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-BACK TO PRODUCTS\"]/android.widget.TextView");
	private By product_price_lbl = AppiumBy.accessibilityId("test-Price");
	
	@Step("Get product title step ...")
	public String getProductTitle() {
		MyLogger.info("Get product title");
		return getElementActions().textGet(product_title_lbl);
	}
	
	@Step("Get product description text step ...")
	public String getProductDescribtion() {
		MyLogger.info("Get product description text");
		return getElementActions().textGet(product_description_txt);
	}
	
	@Step("Get product price step ...")
	public String getProductPrice() {
		MyLogger.info("Get product price");
		return getElementActions().textGet(product_price_lbl);
	}
	
	@Step("Press back to product button step ...")
	public void pressBackToProductButton() {
		MyLogger.info("Press back to product button");
		getElementActions().clickOn(back_to_product_btn);
	}
	
	@Step("Get Expected product details step ...")
	public String getExpectedProductDetailsData(String key)
	{
		String result = productdetailsdata.getString(key);
		MyLogger.info("Get Expected product details data -> " + result);
		return result;
	}
	
	@Step("Scroll Down step ...")
	public void scrollDownToPrice()
	{
		getElementActions().swipeElementAndroidByText("29.99");
		MyLogger.info("scroll To Price");
	}
}
