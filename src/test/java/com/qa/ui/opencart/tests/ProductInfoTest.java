package com.qa.ui.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.ui.opencart.base.BaseTest;

public class ProductInfoTest extends BaseTest {
	
	@BeforeClass
	public void productInfoSetup() {
		//acctPage = new AccountsPage(driver);
		acctPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] {
			{"MacBook", "MacBook Pro", 4},
			{"MacBook", "MacBook Air", 4},
			{"iMac", "iMac", 3},
			{"Samsung", "Samsung SyncMaster 941BW", 1}
		};
		//This data provider is entering the same Product name only in the search field and not clearing every time 
		//need to look into it and fix it 
	}
	
	@Test(dataProvider = "getSearchData" )
	public void productImagesTest(String searchKey, String productName, int imageCount) {
		searchResultsPage = acctPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), imageCount);
		
	}
	
	@Test
	public void productInfoTest() {
		searchResultsPage = acctPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> productDetailsMap = productInfoPage.getProductDetails();
		
		softAssert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(productDetailsMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productDetailsMap.get("Reward Points"), "800");
		
		softAssert.assertEquals(productDetailsMap.get("productPrice"), "$2,000.00");
		softAssert.assertEquals(productDetailsMap.get("productExTaxPrice"), "$2,000.00");
		
		softAssert.assertAll();
		
		
	}

}
