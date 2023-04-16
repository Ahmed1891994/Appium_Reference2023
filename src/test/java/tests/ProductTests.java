package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.qameta.allure.Attachment;
import pages.LoginPage;
import pages.MenuPage;
import pages.ProductDetailsPage;
import pages.ProductPage;
import pages.SettingsPage;

@Listeners(utils.Listener.class)

public class ProductTests extends TestBase{
	LoginPage loginpage;
	MenuPage menupage;
	SettingsPage settingspage;
	ProductPage productpage;
	ProductDetailsPage productdetailspage;
	
	@Attachment
	@Test(description="verify Product On Product Page.")
	public void verifyProductOnProductPage()
	{	
		loginpage = new LoginPage();
		loginpage.pressLogin("validuser");
		productpage = new ProductPage();
		Assert.assertEquals(productpage.getProductTitle(1),productpage.getExpectedProductData("product_title") );
		Assert.assertEquals(productpage.GetProductPrice(1),productpage.getExpectedProductData("product_price") );
		menupage = new MenuPage();
		menupage.pressSettingsBtn();
		settingspage = new SettingsPage();
		settingspage.pressLogoutBtn();
	}

	@Attachment
	@Test(description="verify Product On Product Details Page.")
	public void verifyProductOnProductDetailsPage()
	{	
		loginpage = new LoginPage();
		loginpage.pressLogin("validuser");
		productpage = new ProductPage();
		productpage.enterProduct(1);
		productdetailspage = new ProductDetailsPage();
		Assert.assertEquals(productdetailspage.getProductTitle(),productdetailspage.getExpectedProductDetailsData("product_title") );
		productdetailspage.scrollDownToPrice();
		Assert.assertEquals(productdetailspage.getProductDescribtion(),productdetailspage.getExpectedProductDetailsData("product_describtion_text"));
		Assert.assertEquals(productdetailspage.getProductPrice(),productdetailspage.getExpectedProductDetailsData("product_price"));

		
		menupage = new MenuPage();
		menupage.pressSettingsBtn();
		settingspage = new SettingsPage();
		settingspage.pressLogoutBtn();
	}
}
