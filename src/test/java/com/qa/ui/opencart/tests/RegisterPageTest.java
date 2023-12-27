package com.qa.ui.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.ui.opencart.base.BaseTest;

public class RegisterPageTest extends BaseTest {
	
	@BeforeClass
	public void regSetup() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	public String getRandomEmail(String firstName){
		return firstName + System.currentTimeMillis()+"@testmail.com";
		//return "automationtesting" + UUID.randomUUID()+"@testmail.com";
	}
	
	//We can pass first name to getRandomEmail method and append that with "AutomationTesting" string
	//so that in registration e-mail will have as first string 
	
	@DataProvider
	public  Object[][] getUserRegTestData(){
		return new Object[][] {
			{"Ganesh", "Pillai", "123456889", "test33@12", "yes"},
			{"Parvathi", "Devi", "123456689", "test62@12", "yes"},
		};
	}
	
	@Test(dataProvider = "getUserRegTestData" )
	public void userRegTest(String firstName, String lastName, 
			String phNum, String password, String subscribe) {
		boolean isRegistrationDone = registerPage.userRegisteration
				(firstName, lastName, getRandomEmail(firstName), phNum, password, subscribe);
		
		System.out.println(getRandomEmail(firstName));
		Assert.assertTrue(isRegistrationDone);
	}

}
