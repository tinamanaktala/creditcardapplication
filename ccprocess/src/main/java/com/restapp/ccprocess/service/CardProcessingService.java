package com.restapp.ccprocess.service;

import java.util.List;

import com.restapp.ccprocess.model.CreditCard;

public interface CardProcessingService {
	
	public List<CreditCard> getCards();
	public CreditCard addCard(CreditCard card);

}
