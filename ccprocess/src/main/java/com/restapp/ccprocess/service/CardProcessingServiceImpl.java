package com.restapp.ccprocess.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapp.ccprocess.dao.CardProcessingRepository;
import com.restapp.ccprocess.model.CreditCard;

@Service
@PreAuthorize("hasAuthority('ROLE_USER')")
public class CardProcessingServiceImpl implements CardProcessingService {
	
	@Autowired
	CardProcessingRepository cardRepository;

	@Override
	public List<CreditCard> getCards() {
		// TODO Auto-generated method stub
		return cardRepository.findAll();
	}

	@Override
	public CreditCard addCard(CreditCard card) {
		// TODO Auto-generated method stub
		return cardRepository.save(card);
	}

}
