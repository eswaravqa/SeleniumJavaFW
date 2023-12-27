package com.qa.ui.opencart.tests;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.ui.opencart.base.BaseTest;
import com.qa.ui.opencart.constants.AppConstants;


public class AccountPageTest extends BaseTest {
	
	@BeforeClass
	public void accountSetup() {
		//acctPage = new AccountsPage(driver);
		acctPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test
	public void accountPageTitleTest() {
		Assert.assertEquals(acctPage.getAccountPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test
	public void accountPageUrlTest() {
		Assert.assertTrue(acctPage.getAccountPageUrl().contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION));
	}
	
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(acctPage.isLogoutLinkExist());
	}
	
	@Test
	public void isSearchFieldExistTest() {
		Assert.assertTrue(acctPage.isSearchFieldExist());
	}

	@Test
	public void accountPageHeadersCountTest() {
		List<String> actualAccountPageHeadersList = acctPage.getAccountsHeaders();
		System.out.println(actualAccountPageHeadersList);
		Assert.assertEquals(actualAccountPageHeadersList.size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	
	@Test
	public void accountPageHeadersTest() {
		List<String> actualAccountPageHeaders = acctPage.getAccountsHeaders();
		System.out.println("Before Sorting"+actualAccountPageHeaders);
		Collections.sort(actualAccountPageHeaders);
		System.out.println("After Sorting"+actualAccountPageHeaders);
		Collections.sort(AppConstants.ACCOUNTS_PAGE_HEADERS_LIST);
		Assert.assertEquals(actualAccountPageHeaders, AppConstants.ACCOUNTS_PAGE_HEADERS_LIST);
		
		//Note: In above method what if order is different 
		//TO solve above problem, we need to sort both the lists 
		
	}
	
	@Test
	public void searchTest() {
			searchResultsPage = acctPage.doSearch("macbook");
			productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
			String actualHeaderName = productInfoPage.getProductHeaderName();
			Assert.assertEquals(actualHeaderName, "MacBook Pro");
			
	}
	
	//Notes for above Test 
	//Above is called Zig Zag Pattern 
	//SearchTest() is calling acctPage reference which is calling Login() >> Page#1
	//Login() is calling Account Page >> Page#2
	//AccountPage is calling Search Results Page >> Page#3
	//Search Results Page is calling Product Info Page >> Page#4
	//We are doing validations (Assert) on Page#4
	
	
	
	
	
}
