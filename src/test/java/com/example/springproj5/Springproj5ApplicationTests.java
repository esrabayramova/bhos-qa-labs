package com.example.springproj5;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class Springproj5ApplicationTests {

	TestRestTemplate restTemplate = new TestRestTemplate();
	TestRestTemplate restTemplate_2 = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	HttpHeaders headers_2 = new HttpHeaders();

	String api_key = System.getenv("FIREBASE_API_KEY");

	String email = System.getenv("FIREBASE_AUTH_EMAIL");

	String password = System.getenv("FIREBASE_AUTH_PASSWORD");

	String collection_users = "https://firestore.googleapis.com/v1/projects/spring-boot-proj-255f3/databases/(default)/documents/users/";
	String firebase_auth = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=";
	//String id_token;
	//String user_id;

	public String authorize() throws JSONException {
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("email", email);
		jsonobj.put("password", password);
		HttpEntity<String> http_entity = new HttpEntity<>(jsonobj.toString(), headers);
		ResponseEntity<String> response_entity = restTemplate.exchange(firebase_auth+api_key, HttpMethod.POST, http_entity, String.class);
		//JSONObject response_body = new JSONObject(response_entity.getBody());
		//return response_body;
		return response_entity.getBody();

		//String id_token = (String) response_body.get("idToken");
		//String user_id = (String) response_body.get("localId");
		//assertFalse(id_token.isEmpty());
		//assertFalse(user_id.isEmpty());

	}

	@Test
	void contextLoads() {
	}

	@Test
	public void end_to_end_testing() throws JSONException {
		String response_entity = authorize();
		JSONObject response_body = new JSONObject(response_entity);
		String id_token = (String) response_body.get("idToken");
		String user_id = (String) response_body.get("localId");
		headers_2.put("Authorization", Collections.singletonList("Bearer ".concat(id_token)));
		HttpEntity<String> http_entity_2 = new HttpEntity<>(null, headers_2);
		ResponseEntity<String> response_entity_2 = restTemplate_2.exchange(collection_users.concat(user_id), HttpMethod.GET, http_entity_2, String.class);
		JSONObject response_body_2 = new JSONObject(response_entity_2.getBody());

		System.out.println(http_entity_2);
		System.out.println(response_entity_2);
		System.out.println(response_body_2);
		System.out.println(id_token);
		System.out.println(user_id);

		String user = "{\"name\":{\"stringValue\":\"Name1\"},\"surname\":{\"stringValue\":\"Surname1\"},\"phone\":{\"stringValue\":\"123456789\"}}";
		String fields = (String) response_body_2.get("fields");

		assertTrue(user.equals(fields));
	}
}
