package com.restapp.ccprocess.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.restapp.ccprocess.dao.CardProcessingRepository;
import com.restapp.ccprocess.model.CreditCard;

@ExtendWith(MockitoExtension.class)
public class CardProcessingServiceImplTest {
	
	@Autowired
	@InjectMocks
	CardProcessingServiceImpl cardProcessingService;
	 
	@Mock
	CardProcessingRepository cardRepository;
	
	
	@Test
	public void testGetCards()
	{
		CreditCard card = new CreditCard();
		card.setBalance(0L);
		card.setCardHolderName("Jane");
		card.setCardLimit(1000L);
		card.setCardNumber("8763");
		List<CreditCard> cards = new ArrayList<>();
		cards.add(card);
		
		when(cardRepository.findAll()).thenReturn(cards);
		
		//test
		List<CreditCard> cardsList = cardProcessingService.getCards();
		assertEquals(1,cardsList.size());
	}
	
	@Test
	public void testaddCard()
	{
		CreditCard card = new CreditCard();
		card.setBalance(0L);
		card.setCardHolderName("Jane");
		card.setCardLimit(1000L);
		card.setCardNumber("8763");
		
		when(cardRepository.save(card)).thenReturn(card);
		
		//test
		cardProcessingService.addCard(card);
		verify(cardRepository,times(1)).save(card);
	}

}
