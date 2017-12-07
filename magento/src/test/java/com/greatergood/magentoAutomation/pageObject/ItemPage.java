package com.greatergood.magentoAutomation.pageObject;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.greatergood.magentoAutomation.utility.RandomDataGenerator;

/**
 * @author Varduhi
 */

public class ItemPage extends BasePage<ItemPage> {

	@FindBy(id = "itemName")
	public WebElement itemNameh1;

	@FindBy(id = "itemID")
	public WebElement itemIdSpan;

	@FindBy(id = "productImage")
	public WebElement productImage;

	@FindBy(how = How.ID, using = "addToCartPrice")
	public WebElement itemPriceDiv;

	@FindBy(className = "item-quantity-minus")
	public WebElement itemQuantityMinusIcon;

	@FindBy(className = "item-quantity-input")
	public WebElement itemQuantityInput;

	@FindBy(className = "item-quantity-plus")
	public WebElement itemQuantityPlusIcon;

	@FindBy(css = "input[id^='addToCartButton']")
	public WebElement addToCartButton;

	@FindBy(xpath = "//span[contains(text(),'Please select from among the options provided.')]")
	public WebElement pleaseSelectFromAmongTheOptionsProvided;

	public By monthlyDeliveryCheckBox = By.cssSelector("input[id='monthlyDelivery']");
	public By salePreice = By.cssSelector("strong[class='final-price']");
	public By originalPrice = By.cssSelector("span[id^='original']");
	public By feedBackErrorMessage = By.cssSelector("li[class='feedbackPanelERROR']");
	
	public ItemPage(WebDriver driver) {
		super(driver);

	}

	public boolean isItemOnSale(){
		if (driver.findElements(By.xpath("//strong[starts-with(@id, 'sale')][not(contains(@style,'display:none'))]")).size()>0){
			return true;
		} else {
			return false;
		}
	}
	
	public void selectOption() {

		if (driver.findElements(By.cssSelector("select[id^='displayOptionsSelect']")).size() > 1) {
			WebElement activeBox = driver.findElement(By
					.xpath("//select[starts-with(@id, 'displayOptionsSelect')][not(contains(@disabled,'disabled'))]"));
			String disabledBoxId = driver
					.findElement(By
							.xpath("//select[starts-with(@id, 'displayOptionsSelect')][contains(@disabled,'disabled')]"))
					.getAttribute("id");

			selectByIndex(activeBox, 1);
			waitForCondition(ExpectedConditions.elementToBeClickable(By.id(disabledBoxId)));
			WebElement secondActiveBox = driver.findElement(By.id(disabledBoxId));
			selectByIndex(secondActiveBox, 1);
			waitForCondition(
					ExpectedConditions.elementToBeSelected(new Select(secondActiveBox).getFirstSelectedOption()));
		} else if (driver.findElements(By.cssSelector("select[id^='displayOptionsSelect']")).size() == 1) {
			WebElement activeBox = driver.findElement(By
					.xpath("//select[starts-with(@id, 'displayOptionsSelect')][not(contains(@disabled,'disabled'))]"));
			selectByIndex(activeBox, 1);
			waitForCondition(ExpectedConditions.elementToBeSelected(new Select(activeBox).getFirstSelectedOption()));
		}
	}

	public void selectOptionAndSubscribe() {

		if (driver.findElements(By.cssSelector("select[id^='displayOptionsSelect']")).size() > 0) {

			List<WebElement> listOfProductVersionsWithActiveCheckBox = driver.findElements(By.xpath(
					"//select[@class='itemOptions']/option[not(contains(., 'Choose One'))][not(contains(., '$0.'))][not(contains(., '$1.'))][not(contains(., '$2.'))][not(contains(., '$3.'))][not(contains(., '$4.'))][not(contains(., '$1 '))][not(contains(., '$2 '))][not(contains(., '$3 '))][not(contains(., '$4 '))]"));
			int randomIndex = new RandomDataGenerator().getRandomIntegerInRange(0,
					listOfProductVersionsWithActiveCheckBox.size() - 1);

			WebElement selectBox = driver.findElement(By.cssSelector("select[id^='displayOptionsSelect']"));
			selectByValue(selectBox, listOfProductVersionsWithActiveCheckBox.get(randomIndex).getAttribute("value"));

			waitForAjax();
			driver.findElement(monthlyDeliveryCheckBox).click();

		}
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isLoaded() {
		try {

			System.out.println("ItemPage is loading...");
			String url = driver.getCurrentUrl();
			assertTrue(url.contains("/item/"), "Not on item page - " + url);

			// Make sure page is loaded
			assertTrue(itemNameh1.isDisplayed());
			assertTrue(itemIdSpan.isDisplayed());
			assertTrue(productImage.isDisplayed());
			assertTrue(itemPriceDiv.isDisplayed());
			assertTrue(itemQuantityMinusIcon.isDisplayed());
			assertTrue(itemQuantityInput.isDisplayed());
			assertTrue(itemQuantityPlusIcon.isDisplayed());
			assertTrue(addToCartButton.isDisplayed());

		} catch (Exception e) {
			throw new Error("ItemPage is't fully loaded - " + e.getMessage());
		}

	}

}
