package com.restapp.ccprocess.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapp.ccprocess.exception.MissingFieldsException;
import com.restapp.ccprocess.model.CreditCard;
import com.restapp.ccprocess.request.CardPayload;
import com.restapp.ccprocess.service.CardProcessingService;
import com.restapp.ccprocess.validations.Utility;

@RestController
@RequestMapping(path = "v1/accounts")
public class CardResource {

	@Autowired
	CardProcessingService cardService;

	@PostMapping(value = "/creditcards/cards", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> addCard(@Valid @RequestBody CardPayload cardPayload) {
		boolean isValidPayload = Utility.checkMandatoryFields(cardPayload);
		if (!isValidPayload) {
			throw new MissingFieldsException();
		}
		CreditCard card = Utility.mapPayloadToEntity(cardPayload);
		EntityModel<CreditCard> resource = EntityModel.of(cardService.addCard(card));
		resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllCreditCards())
				.withRel("allCreditCards"));
		resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).addCard(cardPayload))
				.withSelfRel());

		return new ResponseEntity<>(resource, HttpStatus.CREATED);
	}

	@GetMapping(value = "/creditcards", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> getAllCreditCards() {
		CollectionModel<CreditCard> resource = CollectionModel.of(cardService.getCards());
		resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).addCard(new CardPayload()))
				.withRel("addCard"));
		resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllCreditCards())
				.withSelfRel());

		return new ResponseEntity<>(resource, HttpStatus.OK);
	}

}
