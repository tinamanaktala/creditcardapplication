package com.restapp.ccprocess.resource;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapp.ccprocess.model.CreditCard;
import com.restapp.ccprocess.service.CardProcessingService;

@WebMvcTest(CardResource.class)
@ActiveProfiles("default")
public class CardResourceTest {
	
@Autowired
MockMvc mockMvc;

@MockBean
CardProcessingService cardProcessingService;

@Autowired
ObjectMapper mapper;



@Test
public void addCard_success() throws Exception
{
	CreditCard savedCard = new CreditCard();
	savedCard.setBalance(0L);
	savedCard.setCardHolderName("Jane");
	savedCard.setCardLimit(1000L);
	savedCard.setCardNumber("8763");
	savedCard.setCardId(1L);
	savedCard.setCreatedDate(new Date());
	
	Mockito.when(cardProcessingService.addCard(any(CreditCard.class))).thenReturn(savedCard);
	MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/v1/accounts/creditcards/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(savedCard));

    mockMvc.perform(mockRequest)
            .andExpect(status().isCreated())
    		.andExpect(jsonPath("$.cardHolderName", Matchers.is("Jane")));
}

@Test
public void getAllCreditCards_success() throws Exception
{
	CreditCard card = new CreditCard();
	card.setBalance(0L);
	card.setCardHolderName("Jane");
	card.setCardLimit(1000L);
	card.setCardNumber("8763");
	card.setCardId(1L);
	card.setCreatedDate(new Date());
	
	List<CreditCard> cardList = Arrays.asList(card);
	
	Mockito.when(cardProcessingService.getCards()).thenReturn(cardList);
	 
	    mockMvc.perform(MockMvcRequestBuilders.get("/v1/accounts/creditcards"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$._embedded.creditCardList", Matchers.hasSize(1)));
	
}

//this test checks for invalid card that doesnt comply with Luhn10
@Test
public void addCard_testInvalidCardException() throws Exception
{
	CreditCard savedCard = new CreditCard();
	savedCard.setBalance(0L);
	savedCard.setCardHolderName("Jane");
	savedCard.setCardLimit(1000L);
	savedCard.setCardNumber("8763333333");
	savedCard.setCardId(1L);
	savedCard.setCreatedDate(new Date());
	
	MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/v1/accounts/creditcards/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(savedCard));
	
	mockMvc.perform(mockRequest)
     .andExpect(status().isBadRequest())
     .andExpect(jsonPath("$.message", Matchers.is("Invalid card data")));
     
}
//this test checks for any mandatory field missing in the request
@Test
public void addCard_testMissingFieldsException() throws Exception
{
	CreditCard savedCard = new CreditCard();
	savedCard.setBalance(0L);
	savedCard.setCardHolderName("Jane");
	savedCard.setCardNumber("8763333333");
	savedCard.setCardId(1L);
	savedCard.setCreatedDate(new Date());
	
	MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/v1/accounts/creditcards/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(savedCard));
	
	mockMvc.perform(mockRequest)
     .andExpect(status().isBadRequest())
     .andExpect(jsonPath("$.message", Matchers.is("Missing mandatory fields")));
}


}
