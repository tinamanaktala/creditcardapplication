package com.restapp.ccprocess.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.restapp.ccprocess.dao.CardProcessingRepository;
import com.restapp.ccprocess.model.CreditCard;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CardProcessingRepositoryTest {
	
	@Autowired
	CardProcessingRepository cardProcessingRepository;
	
	private  CreditCard card;
	
	
	 @BeforeEach
	    public void setUp() {
		    card = new CreditCard();
			card.setBalance(0L);
			card.setCardHolderName("Jane");
			card.setCardLimit(1000L);
			card.setCardId(1L);
			card.setCardNumber("8763");
			card.setCreatedDate(new Date());
	    }
	 
	 @Test
	 public void givenCardToAddShouldReturnAddedCard(){
		  CreditCard savedCard = cardProcessingRepository.save(card);
	      assertEquals(1, savedCard.getCardId());
	 }
	 
	 @Test
	 public void GetAllCardsShouldReturnListOfAllCards(){
	     
		 cardProcessingRepository.save(card);
	      List<CreditCard> cards = (List<CreditCard>) cardProcessingRepository.findAll();
	      assertEquals("8763", cards.get(0).getCardNumber());
	 }

}
