package com.greatergood.magentoAutomation.component;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.greatergood.magentoAutomation.pageObject.BasePage;

public class FooterComponent extends BasePage<FooterComponent> {

	@FindBy(xpath = "//ul/li[contains(.,'Search Terms')]")
	public WebElement searchTerms;
	@FindBy(xpath = "//ul/li[contains(.,'Privacy and Cookie Policy')]")
	public WebElement privacyCookiePolicy;
	@FindBy(xpath = "//ul/li[contains(.,'Advanced Search')]")
	public WebElement advancedSearch;
	@FindBy(xpath = "//ul/li[contains(.,'Orders and Returns')]")
	public WebElement ordersReturns;
	@FindBy(xpath = "//ul/li[contains(.,'Contact Us')]")
	public WebElement contactUs;
	@FindBy(css = "input[id='newsletter']")
	public WebElement emailAdress;
	@FindBy(css = "button[class^='action subscribe']")
	public WebElement subscribeButton;

	public FooterComponent(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isLoaded() {

		System.out.println("FooterComponent is loading");

		try {
			assertTrue(searchTerms.isDisplayed());
			assertTrue(privacyCookiePolicy.isDisplayed());
			assertTrue(advancedSearch.isDisplayed());
			assertTrue(ordersReturns.isDisplayed());
			assertTrue(contactUs.isDisplayed());
			assertTrue(emailAdress.isDisplayed());
			assertTrue(subscribeButton.isDisplayed());

		} catch (Exception e) {
			throw new Error("FooterComponent is't be fully loaded - " + e.getMessage());
		}

	}

}
