package com.qa.ui.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.ui.opencart.constants.AppConstants;
import com.qa.ui.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	
	//Note: Map is Interface. HashMap is class. The following declaration is Top casing 
	//Child Class object (HasMap) referred by parent Interface (Map) 
	//Hash Map will not get the product details in order 
	//to get the right order we should use Linked Hash Map 
	
	//private Map<String, String>  productMap = new HashMap<String, String>();
	private Map<String, String>  productMap = new LinkedHashMap<String, String>();
	
	//In case if we want to sort Map values in alphabetical order (based on Key) we should use Tree
	//private Map<String, String>  productMap = new TreeMap<String, String>();
	
	
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(this.driver);
	}
	
	public String getProductHeaderName() {
		String productHeaderText =  eleutil.doElementGetText(productHeader);
		System.out.println("Product Header:"+ productHeaderText);
		
		return productHeaderText;
	}
	
	public int getProductImagesCount() {
		int imagesCount =  eleutil.waitForVisibilityOfElements(productImages, AppConstants.MEDIUM_DEFAULT_WAIT).size();
		System.out.println("Product: "+ getProductHeaderName() + " -- Images Count:"+imagesCount);
		return imagesCount;
	}
	
	private void getProductMetaData() {
		List<WebElement> metaDataList = eleutil.waitForVisibilityOfElements(productMetaData, AppConstants.MEDIUM_DEFAULT_WAIT);
		
		for (WebElement e : metaDataList) {
			String metaDataText = e.getText();
			String metaDataKey = metaDataText.split(":")[0].trim();
			String metaDataVal = metaDataText.split(":")[1].trim();
			productMap.put(metaDataKey, metaDataVal);
		}
		
	}
	
	private void getProductPriceData() {
		List<WebElement> productPriceList = eleutil.waitForVisibilityOfElements(productPriceData, AppConstants.MEDIUM_DEFAULT_WAIT);
		String productPrice = productPriceList.get(0).getText();
		String productExTaxPrice = productPriceList.get(1).getText().split(":")[1].trim();
		
		productMap.put("productPrice", productPrice);
		productMap.put("productExTaxPrice", productExTaxPrice);
		
		
	}
	
	public Map<String, String> getProductDetails() {
		productMap.put("productName", getProductHeaderName());
		getProductMetaData();
		getProductPriceData();
		
		System.out.println(productMap);
		return productMap;
		
	}
	

}
