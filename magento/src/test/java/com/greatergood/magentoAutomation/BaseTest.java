package com.greatergood.magentoAutomation;

import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.greatergood.magentoAutomation.manager.DriverManager;
import com.greatergood.magentoAutomation.manager.DriverManager.Browser;
import com.greatergood.magentoAutomation.manager.DriverManager.Machine;
import com.greatergood.magentoAutomation.manager.DriverManager.OS;
import com.greatergood.magentoAutomation.utility.Constant;

public class BaseTest {
	private ThreadLocal<RemoteWebDriver> threadDriver = null;
	
	private ThreadLocal<String> browser = null;

	@BeforeClass(alwaysRun = true)
	@Parameters({ "machine", "os", "os_version", "browser", "browser_version", "device", "deviceOrientation" })
	public void setUp(Machine machine, OS os, String osVersion, Browser browserName, String browserVersion,
			String device, String deviceOrientation) throws MalformedURLException {

		RemoteWebDriver driver = new DriverManager().startDriver(machine, os, osVersion, browserName, browserVersion,
				device, deviceOrientation, this.getClass().getSimpleName());
		threadDriver = new ThreadLocal<RemoteWebDriver>();
		threadDriver.set(driver);
		browser = new ThreadLocal<String>();
		browser.set(browserName.name());

		try {
			System.out.println("Driver is  = " + driver);
			getDriver().get(Constant.BASE_URL + Constant.ARS_SITE);

			if (os.equals(OS.WINDOWS) || os.equals(OS.MAC))
				getDriver().manage().window().maximize();

			if ("Safari".equals(browserName))
				closeAdditionalTabs();

		} catch (Exception ex) {
			Assert.fail("Something wrong with openning base URL " + ex.getMessage());
		}

	}

	public void closeAdditionalTabs() {
		String originalHandle = getDriver().getWindowHandle();
		String currentURL = Constant.BASE_URL + Constant.ARS_SITE;
		ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
		if (tabs.size() > 1) {
			for (String handle : tabs) {
				if (!handle.equals(originalHandle)) {

					try {
						((JavascriptExecutor) getDriver()).executeScript("confirm = function(message){return true;};");
						((JavascriptExecutor) getDriver()).executeScript("alert = function(message){return true;};");
						((JavascriptExecutor) getDriver()).executeScript("prompt = function(message){return true;}");

						getDriver().switchTo().window(handle);
						System.out.println("Tab is closed=" + getDriver().getCurrentUrl());
						getDriver().close();
						System.out.println("driver is closed");

						getDriver().switchTo().window(originalHandle);
						System.out.println("storefront driver.getWindowHandle() = " + getDriver().getWindowHandle());

						System.out.println("CurrentURL = " + currentURL);
						getDriver().get(currentURL);

						WebDriverWait wait = new WebDriverWait(getDriver(), Constant.PAGE_LOAD_MAX_WAIT_TIME);
						wait.until(ExpectedConditions.titleIs("Animal Rescue - Pet Shop"));

					} catch (Exception e) {

						System.out.println("Couldn't close second tab: " + e.getLocalizedMessage());
					}
				}
			}

		}
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {

		System.out.println("AfterClass: driver = " + getDriver());
		getDriver().quit();
		threadDriver.remove();
		browser.remove();
	}

	public RemoteWebDriver getDriver() {
		return threadDriver.get();
	}

	public String getBrowser() {
		return browser.get();
	}

	public boolean isSameDay(Date date1, Date date2) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);
		boolean sameYear = calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
		boolean sameMonth = calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
		boolean sameDay = calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
		return (sameDay && sameMonth && sameYear);
	}
	
	public Date getTime(Date date, String dateFormat, String timeZone ) throws ParseException{
		DateFormat df = new SimpleDateFormat(dateFormat);
		df.setTimeZone(TimeZone.getTimeZone(timeZone));
		return df.parse(df.format(date));
	}

}
