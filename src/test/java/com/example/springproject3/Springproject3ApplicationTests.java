package com.example.springproject3;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class Springproject3ApplicationTests {

	@Test
	void contextLoads() {
	}

	private Map<String, Object> getBook(String new_title, String new_author, Integer new_year_published) {
		Map<String, Object> data = new HashMap<>();
		String title = new_title;
		String author = new_author;
		Integer year_published = new_year_published;

		data.put("title", title);
		data.put("author", author);
		data.put("year_published", year_published);

		return data;
	}

	@Test
	public void addBookTest() throws ExecutionException, InterruptedException {
		Firestore db = FirestoreClient.getFirestore();
		DocumentReference docRef = db.collection("books").document("4");

		Map<String, Object> data = getBook("To kill a mockingbird", "Harper Lee", 1960);

		ApiFuture<WriteResult> result = docRef.set(data);

		String update_time = result.get().getUpdateTime().toString();

		assertFalse(update_time.isEmpty());
	}

	@Test
	public void getBooksTest() throws ExecutionException, InterruptedException {

		Firestore db = FirestoreClient.getFirestore();
		//DocumentReference docRef = db.collection("books").document("5");

		Map<String, Object> data = getBook("The kite runner", "Khaled Hosseini", 2003);
		DocumentReference docRef = db.collection("books").document(data.get("title").toString());
		ApiFuture<WriteResult> result = docRef.set(data);

		//String update_time = result.get().getUpdateTime().toString();

		DocumentReference docRef2 = db.collection("books").document(data.get("title").toString());

		ApiFuture<DocumentSnapshot> future = docRef2.get();

		DocumentSnapshot document = future.get();

		assertTrue(document.exists());
		assertThat(data.get("title").toString()).isEqualTo(document.get("title").toString());
		assertThat(data.get("author").toString()).isEqualTo(document.get("author").toString());
		assertThat(data.get("year_published").toString()).isEqualTo(document.get("year_published").toString());
	}


}
