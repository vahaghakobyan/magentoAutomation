package com.greatergood.magentoAutomation.pageObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.SlowLoadableComponent;
import org.openqa.selenium.support.ui.SystemClock;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.greatergood.magentoAutomation.utility.Constant;

public abstract class BasePage<T extends SlowLoadableComponent<T>> extends SlowLoadableComponent<T> {
	protected WebDriver driver;

	public By nextChevronLocator = By.className("calnavright");
	public By calendarDaysLocator = By.cssSelector("td[id^='dateSubscriptionHold'][class*='selectable']>a");

	public BasePage(WebDriver driver) {
		super(new SystemClock(), Constant.PAGE_LOAD_MAX_WAIT_TIME);
		this.driver = driver;
	}

	public WebElement find(By locator) {
		return driver.findElement(locator);
	}

	public List<WebElement> findElements(WebElement parent, By locator) {
		return parent.findElements(locator);
	}

	public WebElement find(WebElement parent, By locator) {
		return parent.findElement(locator);
	}

	public void selectByValue(WebElement element, String value) {

		Select select = new Select(element);
		if (value == null || value.isEmpty()) {
			select.selectByIndex(0);
		} else {
			select.selectByValue(value);
		}

		waitForCondition(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
	}

	public void selectByVisibleText(WebElement element, String value) {

		Select select = new Select(element);
		if (value == null || value.isEmpty()) {
			select.selectByIndex(0);
		} else {
			select.selectByVisibleText(value);
		}
	}

	public void click(WebElement element) {
		element.click();
	}

	public void customClick(WebElement element) {

		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = caps.getBrowserName();

		if ("safari".equals(browserName)) {

			try {
				element.click();
				System.out.println("SECOND CLICK");
				element.click();
			} catch (Exception e) {
				System.out.println("Click on second click = " + e.getMessage());
			}

		} else {
			Actions actions = new Actions(driver);
			actions.click(element).perform();
		}
	}

	public void selectByVisibleText(WebElement parent, By locator, String value) {
		Select select = new Select(find(parent, locator));
		if (value == null || value.isEmpty()) {
			select.selectByIndex(0);
		} else {
			select.selectByVisibleText(value);
		}

		waitForCondition(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(find(parent, locator))));
	}

	public void selectByValue(WebElement parent, By locator, String value) {

		Select select = new Select(find(parent, locator));
		if (value == null || value.isEmpty()) {
			select.selectByIndex(0);
		} else {
			select.selectByValue(value);
		}

		waitForCondition(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(find(parent, locator))));
	}

	public void selectByIndex(WebElement parent, By locator, int index) {

		Select select = new Select(find(parent, locator));
		if (index < select.getOptions().size()) {
			select.selectByIndex(index);
		} else {
			select.selectByIndex(0);
		}

		waitForCondition(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(find(parent, locator))));
	}

	public void selectByIndex(WebElement element, int index) {

		Select select = new Select(element);
		if (index < select.getOptions().size()) {
			select.selectByIndex(index);
		} else {
			select.selectByIndex(0);
		}
	}

	public String getOptionValue(WebElement element) {

		Select selectBox = new Select(element);
		return selectBox.getFirstSelectedOption().getAttribute("value");

	}

	public String getOptionValue(WebElement parent, By locator) {

		Select selectBox = new Select(find(parent, locator));
		return selectBox.getFirstSelectedOption().getAttribute("value");

	}

	public String getOptionText(WebElement parent, By locator) {
		Select select = new Select(find(parent, locator));
		return select.getFirstSelectedOption().getText();
	}

	public String getOptionText(WebElement element) {
		Select select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}

	public void type(WebElement parent, By inputLocator, String text) {
		WebElement element = find(parent, inputLocator);
		element.clear();
		element.sendKeys(text);
	}

	public void type(WebElement input, String text) {
		input.clear();
		input.sendKeys(text);
	}

	public void jsClick(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void click(WebElement parent, By locator) {
		find(parent, locator).click();
	}

	public String getText(WebElement parent, By locator) {
		return find(parent, locator).getText();
	}

	public String getValue(WebElement element) {
		return element.getAttribute("value");
	}

	public String getValue(WebElement parent, By locator) {
		WebElement element = find(parent, locator);
		return element.getAttribute("value");
	}

	public boolean isElementEnabled(WebElement element) {
		return element.isEnabled();

	}

	public boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	public boolean isElementDisplayed(WebElement parent, By locator) {
		return find(parent, locator).isDisplayed();
	}

	public boolean isElementDisabled(WebElement parent, By locator) {
		return !parent.findElement(locator).isEnabled();
	}

	public boolean isElementEnabled(WebElement parent, By locator) {
		return parent.findElement(locator).isEnabled();

	}

	public boolean isElementDisabled(WebElement element) {
		return !element.isEnabled();
	}

	public void waitForCondition(ExpectedCondition<?> pageLoadCondition) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Constant.PAGE_LOAD_MAX_WAIT_TIME, TimeUnit.SECONDS)
				.pollingEvery(Constant.ELEMENT_POLLING_TIME, TimeUnit.SECONDS).ignoring(Exception.class);

		wait.until(pageLoadCondition);

	}

	public void waitForGoogleReviewPopup() {

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		//By frameSelector = By.cssSelector("iframe[src*='https://www.google.com/shopping/customerreviews/optin']");
		By frameSelector = By.cssSelector("iframe[id^='I1']");
		waitForCondition(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameSelector));

		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(frameSelector));
	}

	public void dismissGoogleReviewPopup(String parentHandler) {
		By noButton = By.xpath("//span[contains(text(),'NO')]");
		waitForCondition(ExpectedConditions.and(ExpectedConditions.visibilityOfElementLocated(noButton),
				ExpectedConditions.elementToBeClickable(noButton)));
		click(driver.findElement(noButton));

		driver.switchTo().window(parentHandler);
		driver.switchTo().defaultContent();
		waitForCondition(ExpectedConditions.numberOfWindowsToBe(1));

	}

	public boolean waitForAjax() {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {

					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};

		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}

	public void waitForAttributeToChange(By locator, String attr, String expectedValue) {
		WebDriverWait wait = new WebDriverWait(driver, Constant.ELEMENT_LOAD_MAX_WAIT_TIME);

		wait.until(new ExpectedCondition<Boolean>() {
			private By locator;
			private String attr;
			private String expectedValue;

			private ExpectedCondition<Boolean> init(By locator, String attr, String expectedValue) {
				this.locator = locator;
				this.attr = attr;
				this.expectedValue = expectedValue;
				return this;
			}

			public Boolean apply(WebDriver driver) {
				System.out.println(this.locator);
				WebElement button = driver.findElement(this.locator);
				System.out.println(this.attr);
				String enabled = button.getAttribute(this.attr);
				return (enabled.equals(this.expectedValue));
			}
		}.init(locator, attr, expectedValue));
	}

	public Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal;

	}

	public void openCalendar(WebElement parent, By calendarLocator) {

		click(parent);
		waitForCondition(ExpectedConditions.visibilityOfElementLocated(calendarLocator));
	}
	
	public Date getDate(DateFormat format, String dateString) {
		try {
			return format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Date modifyDateFormat(DateFormat df, Date date){
		try {
			return df.parse(df.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

//	public void addToCart() {
//
//		ItemPage itemPage = PageFactory.initElements(driver, ItemPage.class).get();
//		itemPage.selectOption();
//		itemPage.click(itemPage.addToCartButton);
//		PageFactory.initElements(driver, AddToCartPopup.class).get();
//
//		// waitForCondition(ExpectedConditions.and(
//		// ExpectedConditions.attributeToBe(By.cssSelector("div[id^='addedToCartPopup']"),
//		// "style",
//		// "display: block;"),
//		// ExpectedConditions.elementToBeClickable(itemPage.addToCartButton),
//		// ExpectedConditions.invisibilityOfElementLocated(By.className("wicket-ajax-indicator"))));
//
//		waitForCondition(ExpectedConditions.and(
//				ExpectedConditions.attributeToBe(By.cssSelector("div[id^='addedToCartPopup']"), "style",
//						"display: none;"),
//				ExpectedConditions.elementToBeClickable(itemPage.addToCartButton),
//				ExpectedConditions.invisibilityOfElementLocated(By.className("wicket-ajax-indicator"))));
//
//	}
//
//	public void goToShoppingCart(HeaderComponent headerComponent) {
//		headerComponent.click(headerComponent.cartLinkIcon);
//		ShoppingCartPopup shoppingCartPopup = PageFactory.initElements(driver, ShoppingCartPopup.class).get();
//		shoppingCartPopup.click(shoppingCartPopup.checkoutCartButton);
//	}
//
//	public void fillAddress(String email, String firstName, String lastName, String streetAddress, String country,
//			String city, String state, String postalCode, String phone) {
//
//		AddressPage addressPage = PageFactory.initElements(driver, AddressPage.class).get();
//		addressPage.fillBillingAddress(email, firstName, lastName, streetAddress, country, city, state, postalCode,
//				phone);
//
//		scrollToTop(addressPage.continueCheckoutButton);
//		addressPage.click(addressPage.continueCheckoutButton);
//
//	}
//
//	public void placeOrder(String type, String cartNumber, String securityCode) {
//
//		PaymentPage paymentPage = PageFactory.initElements(driver, PaymentPage.class).get();
//		PageFactory.initElements(driver, SecondaryHeaderComponent.class).get();
//		paymentPage.fillCardInfo(type, cartNumber, securityCode);
//		paymentPage.click(paymentPage.placeOrderButton);
//	}
//
//	public void returnToShop() {
//
//		ThankYouPage thankYouPage = PageFactory.initElements(driver, ThankYouPage.class).get();
//		thankYouPage.scrollToTop(thankYouPage.returnToShopLink);
//		thankYouPage.click(thankYouPage.returnToShopLink);
//	}
//
//	public long checkoutAndSubscribe(HeaderComponent headerComponent, String email, String firstName, String lastName,
//			String streetAddress, String country, String city, String state, String postalCode, String phone,
//			String type, String cartNumber, String securityCode) {
//
//		ItemPage itemPage = PageFactory.initElements(driver, ItemPage.class).get();
//
//		itemPage.selectOptionAndSubscribe();
//		itemPage.click(itemPage.addToCartButton);
//
//		waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id^='addedToCartPopup']")));
//
//		waitForCondition(ExpectedConditions.and(
//				ExpectedConditions.attributeToBe(By.cssSelector("div[id^='addedToCartPopup']"), "style",
//						"display: none;"),
//				ExpectedConditions.elementToBeClickable(itemPage.addToCartButton),
//				ExpectedConditions.invisibilityOfElementLocated(By.className("wicket-ajax-indicator"))));
//		headerComponent.click(headerComponent.cartLinkIcon);
//
//		ShoppingCartPopup shoppingCartPopup = PageFactory.initElements(driver, ShoppingCartPopup.class).get();
//		shoppingCartPopup.click(shoppingCartPopup.checkoutCartButton);
//
//		fillAddress(email, firstName, lastName, streetAddress, country, city, state, postalCode, phone);
//		//placeOrder(type, cartNumber, securityCode);
//		PaymentPage paymentPage = PageFactory.initElements(driver, PaymentPage.class).get();
//		String parentHandler = driver.getWindowHandle();
//		placeOrder(type, cartNumber, securityCode);
//
//		paymentPage.waitForGoogleReviewPopup();
//		paymentPage.dismissGoogleReviewPopup(parentHandler);
//		
//		SecondaryHeaderComponent secondaryHeaderComponent = PageFactory
//				.initElements(driver, SecondaryHeaderComponent.class).get();
//		long orderId = Long.parseLong(secondaryHeaderComponent.orderIdSpan.getText());
//		returnToShop();
//
//		return orderId;
//
//	}

	public boolean isLoadingSpinnerVisible() {

		List<WebElement> loadingSpinners = driver.findElements(By.className("wicket-ajax-indicator"));
		boolean isSpinnerVisible = false;

		for (int i = 0; i < loadingSpinners.size(); i++) {
			isSpinnerVisible = isElementDisplayed(loadingSpinners.get(i));

			if (isSpinnerVisible)
				break;
		}
		return isSpinnerVisible;

	}

	public void scrollToTop(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

}