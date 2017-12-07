package com.greatergood.magentoAutomation.utility;

import java.text.DecimalFormat;

public class Constant {
	
	public static final String BASE_URL = "dev-54ta5gq-gyaqx7oecsg5k.us.magentosite.cloud";

	public static final String MAC_URL = "http://192.168.1.133:5566/wd/hub";

	public static final String ARS_SITE = "/s12t";

	public static final int ARS_SITE_ID = 310;

	public static final int ARS_SITE_AVAILABILITY_BIT_VALUE = 8;

	public static final String CK_SITE = "/store/ck/site";

	public static final int CK_SITE_ID = 351;

	public static final int CK_SITE_ABAILABILITY_BIT_VALUE = 32768;

	public static final String REST_SERVER_URL = "http://nginx-stage-a.greatergood.net";
	// "http://swarm-master-a";
	// "http://docker-arm-a.greatergood.net";
	// "http://xentech-service-a:8080";

	public static final String REST_ITEM_URL = "/nexus-items-2.0";

	public static final String REST_ACCOUNT_URL = "/nexus-accounts-2.1";

	public static final String REST_SHOPPING_CART_URL = "/nexus-shoppingcart-2.0";

	public static final String REST_PAYMENT_URL = "/nexus-payment-2.1";

	// public static final String REST_PRICE_URL = "/nexus-prices-1.2";

	public static final int PAGE_LOAD_MAX_WAIT_TIME = 180;

	public static final int ELEMENT_LOAD_MAX_WAIT_TIME = 90;

	public static final int ELEMENT_POLLING_TIME = 2;

	public static final int CAROUSEL_LOAD_MAX_WAIT_TIME = 200;

	public static final int SAUCEL_LABS_TEST_MAX_DURTATION = 1200;

	public static final int SAUCE_LABS_COMMAND_TIME_OUT = 200;

	public static final int SAUCE_LABS_IDLE_TIME_OUT = 300;

	public static final String PLEASE_USE_NUMBER = "Please use number";

	public static final String PERSONALIZED_ITEM = "This is a personalized item!";

	public static final String ANIMAL_RESCUE_TITLE = "Animal Rescue - Pet Shop";

	public static final String CREATIVE_KIDSTUFF_TITLE = "Educational Toys, Books and Games - Creative Kidstuff";

	public static final String BROWSERSTACK_SERVER_URL = "https://varduhivardanyan1:UEANxUKc6wNzbhuUpzeS@hub-cloud.browserstack.com/wd/hub";

	public static final String SAUCELABS_JOB_NAME = "Storefront";

	public static final String SALESORDER_PROCESSED_STATUS = "PROCESSED";

	public static final DecimalFormat DECIMAL_FORMAT = (DecimalFormat) DecimalFormat.getInstance();

	public static final String SUBSCRIPTION_ACTIVE_STATUS = "Active";

	public static final String SUBSCRIPTION_CANCELLED_STATUS = "Cancelled";

	public static final String SIMPLE_DATE_FORMAT_WITH_DASHES = "yyyy-MM-dd HH:mm:ss";

	public static final String SIMPLE_DATE_FORMAT_WITH_SLASH = "yyyy/MM/dd HH:mm:ss";

	public static final String SIMPLE_DATE_SHORT_FOTMAT = "yyyy-MM-dd";

	public static final String SORT_BY_A_TO_Z = "A-to-Z";

	public static final String MOST_POPULAR = "Most Popular";

	public static final String ALL_SIZES = "All Sizes";

	public static final String ITEMS_48 = "48 items";

	public static final String ITEMS_24 = "24 items";

	public static final String EMPTY_CART_MESSAGE = "There are currently no items in your shopping cart.";

}
