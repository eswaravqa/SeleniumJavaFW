package com.qa.ui.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.ui.opencart.base.BaseTest;
import com.qa.ui.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic#: Design Opencart Login Page")
@Story("User Story #: this is Login page user story")
@Feature("F50: Specific feature test")
public class LoginPageTest extends BaseTest {
	
	@Description("Login Page Title Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Description("Login Page URL Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginPageUrl();
		Assert.assertEquals(actUrl, prop.getProperty("url"));
	}
	
	@Description("Login Page Title Test-Check for a specific text")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 3)
	public void loginPageUrlTestwithContains() {
		String actUrl = loginPage.getLoginPageUrl();
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Description("Login Page: Check for forgot password link exist?")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Description("Login Page: Check if logo exists?")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 5)
	public void appLogoExistTest() {
		Assert.assertTrue(loginPage.isLogoExist());
	}
	
	//contacteswart@gmail.com  || Automation@12
	
	@Description("Login Page: Check if user is able to login successfully")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 6)
	public void successfullLoginTest() {
	acctPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	Assert.assertTrue(acctPage.isLogoutLinkExist());	
	}
	
	//Why priority is given? say while executing tests in alphabetical order what if login test executes, others tests
	//related to Login page will fail. Right? hence Priority is given so that Login page test gets excuted at end
	
	
	

}
