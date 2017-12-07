package com.greatergood.magentoAutomation.utility;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author Varduhi Vardanyan
 */
public class RandomDataGenerator {

	public String generateRandomString() {
		return RandomStringUtils.randomAlphabetic(10);

	}

	public double getRandomNumberInRange(int min, int max) {

		Double randomDouble = ThreadLocalRandom.current().nextDouble(min, max);
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(randomDouble));

	}

	public int getRandomIntegerInRange(int min, int max) {

		Random rand = new Random();

		int randomNum = rand.nextInt(max - min) + min;

		return randomNum;
	}

}
