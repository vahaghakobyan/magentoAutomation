package com.greatergood.magentoAutomation.component;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.greatergood.magentoAutomation.pageObject.BasePage;

public class HeaderComponent extends BasePage<HeaderComponent>{
	
	@FindBy(css = "li[class='greet welcome']>span")
	public WebElement greetWelcome;
	@FindBy(css = "li[class='authorization-link']>a")
	public WebElement signIn;
	@FindBy(xpath = "//div/ul/li[contains(.,'Create an Account')]")
	public WebElement signUp;
	@FindBy(css="a[class='action showcart']")
	public WebElement cartLinkIcon;
	
	public HeaderComponent(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isLoaded() {

		System.out.println("HeaderComponent is loading");

		try {
			assertTrue(greetWelcome.isDisplayed());
			assertTrue(signIn.isDisplayed());
			assertTrue(signUp.isDisplayed());
			assertTrue(cartLinkIcon.isDisplayed());

		} catch (Exception e) {
			throw new Error("HeaderComponent is't be fully loaded - " + e.getMessage());
		}
	}

}
