package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.qameta.allure.Attachment;
import pages.LoginPage;

@Listeners(utils.Listener.class)

public class LoginsTests extends TestBase{
	LoginPage loginpage;
	
	@Attachment
	@Test(description="Valid Login Scenario with valid username and password.")
	public void VerifyLoginSuccessfully()
	{	
		loginpage = new LoginPage();
		loginpage.pressLogin("validuser");
	}
	
	@Attachment
	@Test(description="Invalid Login Scenario with invalid username and valid password.")
	public void VerifyLoginFailedUsingWrongUsername()
	{	
		loginpage = new LoginPage();
		loginpage.pressLogin("invaliduser");
		Assert.assertEquals(loginpage.getPageErrorText(),loginpage.getExpectedErrorText("err_in_login_wrongusername"));
	}
	
	@Attachment
	@Test(description="Invalid Login Scenario with valid username and invalid password.")
	public void VerifyLoginFailedUsingWrongPAssword()
	{	
		loginpage = new LoginPage();
		loginpage.pressLogin("invalidpassword");
		Assert.assertEquals(loginpage.getPageErrorText(),loginpage.getExpectedErrorText("err_in_login_wrongpassword"));
	}
	
	
}
