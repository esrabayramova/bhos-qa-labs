package com.example.springproj4;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    @GetMapping("/")
    public String get_title() {
        String url = "https://api.nytimes.com/svc/books/v3/lists.json?list=Combined Print and E-Book Nonfiction&api-key=LwQ6EpoaaosXjc5WbV0dUW4ZSsUn74yD";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> out = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        JSONObject jsonObject = new JSONObject(out.getBody());
        JSONArray jsonArray = jsonObject.getJSONArray("results");

        List<String> books = new ArrayList<String>();

        for (int i = 0; i < jsonArray.length(); i++){
            //System.out.println(jsonArray.getJSONObject(i));
            System.out.println(jsonArray.getJSONObject(i).getJSONArray("book_details").getJSONObject(0).get("title"));
            //System.out.println(jsonArray.getJSONObject(i).getJSONArray("book_details").getJSONObject(0).get("title").getClass());
            books.add((String) jsonArray.getJSONObject(i).getJSONArray("book_details").getJSONObject(0).get("title"));
            System.out.println(jsonArray.getJSONObject(i).get("rank"));
            //System.out.println(jsonArray.getJSONObject(i).get("rank").getClass());
        }

        return books.toString();
    }

}