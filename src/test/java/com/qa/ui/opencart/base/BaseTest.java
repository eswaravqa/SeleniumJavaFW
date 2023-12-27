package com.qa.ui.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.ui.opencart.driverfactory.DriverFactory;
import com.qa.ui.opencart.pages.AccountsPage;
import com.qa.ui.opencart.pages.LoginPage;
import com.qa.ui.opencart.pages.ProductInfoPage;
import com.qa.ui.opencart.pages.RegisterPage;
import com.qa.ui.opencart.pages.SearchResultsPage;

public class BaseTest {
	
	protected WebDriver driver;
	protected Properties prop;
	DriverFactory df;
	
	
	//Note: All Page References should be created inside the BaseTest
	//Page references 
	protected LoginPage loginPage;
	protected AccountsPage acctPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage registerPage;
	
	protected SoftAssert softAssert;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName)  {
		df = new DriverFactory();
		prop = df.initProperties();
		
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);	
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	

}
