package com.greatergood.magentoAutomation.manager;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.greatergood.magentoAutomation.utility.Constant;

/**
 * @author Varduhi Vardanyan
 */

public class DriverManager {

	public enum Machine {
		REMOTE_MAC, BROWSERSTACK
	}

	public enum OS {
		MAC, WINDOWS, ANDROID, IOS
	};

	public enum Browser {
		FIREFOX, CHROME, SAFARI, EDGE, IE
	};

	public RemoteWebDriver startDriver(Machine machine, OS os, String osVersion, Browser browser, String browserVersion,
			String device, String deviceOrientation, String name) throws MalformedURLException {

		DesiredCapabilities caps = null;
		RemoteWebDriver driver = null;

		switch (machine) {

		case REMOTE_MAC:
			switch (browser) {
			case FIREFOX:
				FirefoxProfile profile = new FirefoxProfile();
				profile.setEnableNativeEvents(true);
				caps = DesiredCapabilities.firefox();
				caps.setCapability(FirefoxDriver.PROFILE, profile);
				break;
			case CHROME:
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-popup-blocking");
				caps = DesiredCapabilities.chrome();
				caps.setCapability(ChromeOptions.CAPABILITY, options);
				break;
			default:
				caps = DesiredCapabilities.safari();
				break;
			}

			driver = new RemoteWebDriver(new URL(Constant.MAC_URL), caps);
			break;

		case BROWSERSTACK:
			caps = new DesiredCapabilities();
			
			switch(browser){
			
			case CHROME:
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-popup-blocking");
				caps.setCapability(ChromeOptions.CAPABILITY, options);
				caps.setCapability("browser_version", browserVersion);
				caps.setCapability("browser", browser);
				break;
				
			case FIREFOX:
				FirefoxProfile profile = new FirefoxProfile();
				profile.setEnableNativeEvents(true);
				caps = DesiredCapabilities.firefox();
				caps.setCapability(FirefoxDriver.PROFILE, profile);
				break;
				
			case SAFARI:
				caps = DesiredCapabilities.safari();
				caps.setCapability("browser_version", browserVersion);
				caps.setCapability("browser", browser);
				break;
			
			}

			switch (os) {

			case WINDOWS:
				caps.setCapability("os", os);
				caps.setCapability("browser_version", browserVersion);
				caps.setCapability("browser", browser);
				break;

			case ANDROID:
				caps.setCapability("device", device);
				caps.setCapability("realMobile", "true");
				caps.setCapability("deviceOrientation", deviceOrientation);
				break;

			default:
				caps.setCapability("device", device);
				caps.setCapability("realMobile", "true");
				caps.setCapability("deviceOrientation", deviceOrientation);
				break;

			}

			String username = System.getenv("BROWSERSTACK_USER");
			String accessKey = System.getenv("BROWSERSTACK_ACCESSKEY");
			String browserstackLocal = System.getenv("BROWSERSTACK_LOCAL");
			String browserstackLocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");

			System.out.println("BROWSERSTACK_USER = " + username);
			System.out.println("BROWSERSTACK_ACCESSKEY = " + accessKey);
			System.out.println("browserstackLocal = " + browserstackLocal);
			System.out.println("browserstackLocalIdentifier = " + browserstackLocalIdentifier);

//			caps.setCapability("browserstack.local", browserstackLocal);
//			caps.setCapability("browserstack.localIdentifier", browserstackLocalIdentifier);
//			caps.setCapability("os_version", osVersion);
//			caps.setCapability("browserstack.selenium_version", "2.53.1");
//			caps.setCapability("browserstack.debug", "true");
//			caps.setCapability("browserstack.video", "true");
//			caps.setCapability("acceptSslCerts", "true");
//			caps.setCapability("name", name);
//			driver = new RemoteWebDriver(
//					new URL("https://" + username + ":" + accessKey + "@hub.browserstack.com/wd/hub"), caps);
			
			
			caps.setCapability("browserstack.local", "true");
			caps.setCapability("browserstack.localIdentifier", browserstackLocalIdentifier);
		//	capabilities.setCapability("os_version", osVersion);
			caps.setCapability("browserstack.selenium_version", "2.53.1");
			caps.setCapability("browserstack.debug", "true");
			caps.setCapability("browserstack.video", "true");
			caps.setCapability("acceptSslCerts", "true");
			caps.setCapability("name", name);
	        driver = new RemoteWebDriver(new URL(
	                "https://" + "vahagn5" + ":" + "mn9REzhebxfcLh9CdqgH" + "@hub.browserstack.com/wd/hub"),
	        		caps);

		}
		return driver;
	}
}
