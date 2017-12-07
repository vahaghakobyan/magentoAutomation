package com.greatergood.magentoAutomation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.greatergood.magentoAutomation.component.FooterComponent;
import com.greatergood.magentoAutomation.component.HeaderComponent;
import com.greatergood.magentoAutomation.component.ItemCardsComponent;
import com.greatergood.magentoAutomation.pageObject.ItemPage;
import com.greatergood.magentoAutomation.utility.RandomDataGenerator;

public class CheckoutTest extends BaseTest{
	
	private HeaderComponent headerComponent;
	private ItemCardsComponent itemCardsComponent;
	private FooterComponent footerComponent;
	private ItemPage itemPage;
	
	@BeforeClass
	public void beforeClass(){
		headerComponent = PageFactory.initElements(getDriver(), HeaderComponent.class).get();
		itemCardsComponent = PageFactory.initElements(getDriver(), ItemCardsComponent.class).get();
		footerComponent = PageFactory.initElements(getDriver(), FooterComponent.class).get();
	}
	
	@Test(priority=0)
	public void checkout(){
		
		int randomIndex = new RandomDataGenerator().getRandomIntegerInRange(0, itemCardsComponent.itemTokenCards.size() - 1);
		WebElement item = itemCardsComponent.itemTokenCards.get(randomIndex);
		itemCardsComponent.click(item);
		itemPage = PageFactory.initElements(getDriver(), ItemPage.class).get();
		headerComponent.get();
	}

}
