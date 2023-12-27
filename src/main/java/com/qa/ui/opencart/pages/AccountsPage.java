package com.qa.ui.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.ui.opencart.constants.AppConstants;
import com.qa.ui.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	// By locators
	private By logoutLink = By.linkText("Logout");
	private By searchBox = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By accountHeaders = By.cssSelector("div#content > h2");

	// Page Constructor
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(this.driver);
	}

	// Page Actions (Methods Or Behaviour)

	public String getAccountPageTitle() {
		String title = eleutil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Account page title: " + title);
		return title;
	}

	public String getAccountPageUrl() {
		String url = eleutil.waitForURLContains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION,
				AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Account page url: " + url);
		return url;
	}

	public boolean isLogoutLinkExist() {
		return eleutil.waitForVisibilityOfElement(logoutLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}

	public void logout() {
		if (isLogoutLinkExist()) {
			eleutil.doClick(logoutLink);
		}
	}

	public boolean isSearchFieldExist() {
		return eleutil.waitForVisibilityOfElement(searchBox, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}

	public List<String> getAccountsHeaders() {
		List<WebElement> headersList = eleutil.waitForVisibilityOfElements(accountHeaders, AppConstants.LONG_DEFAULT_WAIT);
		List<String> headersTextList = new ArrayList<String>();
		for (WebElement e : headersList) {
			String text = e.getText();
			headersTextList.add(text);			
			
		}		
		return headersTextList;
	}

	public SearchResultsPage doSearch(String searchKey) {
		eleutil.waitForVisibilityOfElement(searchBox, AppConstants.SHORT_DEFAULT_WAIT).sendKeys(searchKey);
		eleutil.doClick(searchIcon);
		
		return new SearchResultsPage(driver);
		
	}
		
	}
