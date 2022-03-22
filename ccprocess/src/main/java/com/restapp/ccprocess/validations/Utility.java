package com.restapp.ccprocess.validations;

import java.util.Date;
import java.util.PrimitiveIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import javax.validation.Valid;

import com.restapp.ccprocess.exception.InvalidCardException;
import com.restapp.ccprocess.model.CreditCard;
import com.restapp.ccprocess.request.CardPayload;

public class Utility {

	public static CreditCard mapPayloadToEntity(@Valid CardPayload cardPayload) {
		// this method validates card payload and throws an exception if card is not
		// valid
		CreditCard card = null;
		boolean isNumeric = checkData(cardPayload.getCardNumber());
		if(!isNumeric && cardPayload.getCardNumber().length()>19)
		{
			throw new InvalidCardException();
		}
		boolean isValid = applyLuhnCheck(cardPayload.getCardNumber());
		if (!isValid) {
			throw new InvalidCardException();
		} else {
			card = new CreditCard();
			card.setCardHolderName(cardPayload.getCardHolderName());
			card.setCardNumber(cardPayload.getCardNumber());
			card.setCardLimit(cardPayload.getCardLimit());
			card.setBalance(0L);
			card.setCreatedDate(new Date());

		}

		return card;
	}

	private static boolean checkData(String cardNumber) {
		// this method check if the field contains all numbers
		String regex = "[0-9]+";
		Pattern pattern = Pattern.compile(regex);
		//Find match between given string
        // and regular expression
        // using Pattern.matcher()
        Matcher m = pattern.matcher(cardNumber);
		return m.matches();
	}

	private static boolean applyLuhnCheck(String cardNumber) {
		// this method checks if the card number is valid as per luhn 10 algorithm
		StringBuilder builder = new StringBuilder(cardNumber);
		String revertedString = builder.reverse().toString();
		PrimitiveIterator.OfInt factor =
			      IntStream.iterate(1, i -> 3 - i).iterator();
		int sum = revertedString.chars().map(i -> i - '0').map(i -> i*factor.nextInt()).reduce(0,
				(a, b) -> a + b / 10 + b % 10);
		return (sum % 10) == 0;
	}

	public static boolean checkMandatoryFields(@Valid CardPayload cardPayload) {
		// this method checks if all mandatory fields are present or not
		if ((null != cardPayload.getCardHolderName() || !cardPayload.getCardHolderName().isEmpty())
				&& (null != cardPayload.getCardNumber() || !cardPayload.getCardNumber().isEmpty())
				&& (null != cardPayload.getCardLimit())) {
			return true;
		}
		return false;
	}

}
