package com.qa.ui.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.ui.opencart.constants.AppConstants;
import com.qa.ui.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	
	//By locators: OR (Object Repository)
	private By userName = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img[title='naveenopencart']");
	private By registerLink = By.linkText("Register");
	
	//Page Constructor 
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(this.driver);
		
	}
	
	//Page Actions/Methods:
	@Step("Getting Login Page Titile ")
	public String getLoginPageTitle() {
		String title = eleutil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Login page title: " + title);
		return title;
	}
	
	@Step("Getting login page url ")
	public String getLoginPageUrl() {
		String url = eleutil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Login page url: " + url);
		return url;
	}
	
	@Step("Check if forgot Password link Exist")
	public boolean isForgotPwdLinkExist(){
		//return driver.findElement(forgotPwdLink).isDisplayed();
		return eleutil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	@Step("Check if Logo Exist")
	public boolean isLogoExist(){
		//return driver.findElement(logo).isDisplayed();
		return eleutil.waitForVisibilityOfElement(logo, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	@Step("Login with valid UserName: {0} and Password: {1}")
	public AccountsPage doLogin(String username, String pwd) {
		/*driver.findElement(userName).sendKeys(username);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginBtn).click();
		System.out.println("User is logged in");
		return true;
		*/
		
		eleutil.waitForVisibilityOfElement(userName, AppConstants.SHORT_DEFAULT_WAIT).sendKeys(username);
		eleutil.doSendKeys(password, pwd);
		eleutil.doClick(loginBtn);
		return new AccountsPage(driver);
		//System.out.println("User is logged in");
		//return true;
		
		
	}
	
	public RegisterPage navigateToRegisterPage() {
		eleutil.waitForVisibilityOfElement(registerLink, AppConstants.MEDIUM_DEFAULT_WAIT).click();
		return new RegisterPage(driver);
	}
	
		
	
	
	
	

}
