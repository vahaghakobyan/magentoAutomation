package com.greatergood.magentoAutomation.component;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.greatergood.magentoAutomation.pageObject.BasePage;
import com.greatergood.magentoAutomation.utility.RandomDataGenerator;

public class ItemCardsComponent extends BasePage<ItemCardsComponent>{
	
	@FindBy(css = "li[class='product-item']")
	public List<WebElement> itemTokenCards;
	public By nameLocator = By.cssSelector("strong[class='product-item-name']");
	public By priceLocator = By.cssSelector("span[class='price']");
	

	public ItemCardsComponent(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isLoaded() {

		System.out.println("ItemCardComponent is loading");

		try {
			assertTrue(itemTokenCards.size() > 0);
			int randomIndex = new RandomDataGenerator().getRandomIntegerInRange(0, itemTokenCards.size() - 1);
			itemTokenCards.get(randomIndex).isDisplayed();
			WebElement itemCard = itemTokenCards.get(0);
			itemCard.isDisplayed();
			assertTrue(itemCard.findElement(nameLocator).isDisplayed());
			assertTrue(itemCard.findElement(priceLocator).isDisplayed());
		} catch (Exception e) {
			throw new Error("ItemCardComponent is't be fully loaded - " + e.getMessage());
		}
	}
}
