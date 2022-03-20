package com.restapp.ccprocess.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CardPayload {
	
	@NotNull
	private String cardHolderName;
	
	@NotNull
	@Size(max=19, message="Card number cannot be greater than 19 characters")
	private String cardNumber;
	
	@NotNull
	private Long cardLimit;
	

	/**
	 * @return the cardHolderName
	 */
	public String getCardHolderName() {
		return cardHolderName;
	}

	/**
	 * @param cardHolderName the cardHolderName to set
	 */
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	/**
	 * @param cardHolderName
	 * @param cardNumber
	 * @param cardLimit
	 * @param balance
	 */
	public CardPayload(String cardHolderName, String cardNumber, Long cardLimit) {
		super();
		this.cardHolderName = cardHolderName;
		this.cardNumber = cardNumber;
		this.cardLimit = cardLimit;
	}

	public CardPayload() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	


	@Override
	public String toString() {
		return "CardPayload [cardHolderName=" + cardHolderName + ", cardNumber=" + cardNumber + ", cardLimit="
				+ cardLimit + "]";
	}

	/**
	 * @return the cardLimit
	 */
	public Long getCardLimit() {
		return cardLimit;
	}

	/**
	 * @param cardLimit the cardLimit to set
	 */
	public void setCardLimit(Long cardLimit) {
		this.cardLimit = cardLimit;
	}

	

}
