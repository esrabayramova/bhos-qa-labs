package com.example.springproj4;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class Springproj4ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void non_empty_title() {
		String url = "https://api.nytimes.com/svc/books/v3/lists.json?list=Combined Print and E-Book Nonfiction&api-key=LwQ6EpoaaosXjc5WbV0dUW4ZSsUn74yD";
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> out = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		JSONObject jsonObject = new JSONObject(out.getBody());
		JSONArray jsonArray = jsonObject.getJSONArray("results");

		boolean flag = true;
		for (int i = 0; i < jsonArray.length(); i++){
			String book_title = (String) jsonArray.getJSONObject(i).getJSONArray("book_details").getJSONObject(0).get("title");
			if (book_title.length() == 0){
				flag = false;
			}
		}
		assertTrue(flag);
	}

	@Test
	public void complete_rank() {
		String url = "https://api.nytimes.com/svc/books/v3/lists.json?list=Combined Print and E-Book Nonfiction&api-key=LwQ6EpoaaosXjc5WbV0dUW4ZSsUn74yD";
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> out = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		JSONObject jsonObject = new JSONObject(out.getBody());
		JSONArray jsonArray = jsonObject.getJSONArray("results");

		boolean flag = true;
		for (int i = 0; i < jsonArray.length(); i++){
			Integer book_rank = (Integer) jsonArray.getJSONObject(i).get("rank");
			if (book_rank != i + 1){
				flag = false;
			}
		}
		assertTrue(flag);
	}

}
