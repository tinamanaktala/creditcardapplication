package com.restapp.ccprocess.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CreditCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cardId;
	
	@Column
	private String cardHolderName;
	

	@Column
	private String cardNumber;
	
	@Column
	private Long cardLimit;
	
	@Column
	private Date createdDate;
	
	@Column
	private Long balance;
	
	/**
	 * 
	 */
	public CreditCard() {
		super();
	}

	

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



	/**
	 * @return the cardId
	 */
	public Long getCardId() {
		return cardId;
	}



	/**
	 * @param cardId the cardId to set
	 */
	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}



	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}



	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}



	/**
	 * @return the balance
	 */
	public Long getBalance() {
		return balance;
	}



	/**
	 * @param balance the balance to set
	 */
	public void setBalance(Long balance) {
		this.balance = balance;
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
