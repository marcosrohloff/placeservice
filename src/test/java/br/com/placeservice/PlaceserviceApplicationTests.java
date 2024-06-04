package br.com.placeservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.placeservice.api.PlaceRequest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PlaceserviceApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	@Test
	public void testCreatePlaceSuccess() {
		final String name = "Valid Name";
		final String city = "Valid City";
		final String state = "Valid State";
		final String slug = "valid-name";

		webTestClient
			.post()
			.uri("/places")
			.bodyValue(new PlaceRequest(name, city, state))
			.exchange()
			.expectBody()
			.jsonPath("name").isEqualTo(name)
			.jsonPath("city").isEqualTo(city)
			.jsonPath("state").isEqualTo(state)
			.jsonPath("slug").isEqualTo(slug)
			.jsonPath("createdAt").isNotEmpty()
			.jsonPath("updatedAt").isNotEmpty();

	}

	@Test
	public void testCreatePlaceFailure() {
		final String name = "";
		final String state = "";
		final String city = "";

		webTestClient
        .post()
        .uri("/places")
        .bodyValue(
            new PlaceRequest(name, city, state))
        .exchange()
        .expectStatus().isBadRequest();
	}

}
