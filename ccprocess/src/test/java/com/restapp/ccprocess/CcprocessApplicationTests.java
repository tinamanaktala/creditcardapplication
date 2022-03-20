package com.restapp.ccprocess;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.restapp.ccprocess.resource.CardResource;

@SpringBootTest
class CcprocessApplicationTests {
	
	@Autowired
	CardResource cardController;

	@Test
	void contextLoads() {
		Assertions.assertThat(cardController).isNotNull();
	}

}
