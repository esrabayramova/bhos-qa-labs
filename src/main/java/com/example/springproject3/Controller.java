package com.example.springproject3;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
public class Controller {

    private Map<String, Object> getBook() {
        Map<String, Object> data = new HashMap<>();
        String title = "Adventures of Tom Sawyer";
        String author = "Mark Twain";
        Integer year_published = 1876;

        data.put("title", title);
        data.put("author", author);
        data.put("year_published", year_published);

        return data;
    }

    @RequestMapping("/add")
    public String addBook() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("books").document("3");

        Map<String, Object> data = getBook();

        ApiFuture<WriteResult> result = docRef.set(data);

        System.out.println(result.get().getUpdateTime());

        return "New book added to the database! ";
    }

    @RequestMapping("/get")
    public String getBooks() throws ExecutionException, InterruptedException {
        String response = "";

        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = db.collection("books").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

        for (QueryDocumentSnapshot document : documents) {
            System.out.println("Book: " + document.getId());
            response += "<b>Book: " + document.getId() + "; Title: " + document.get("title") + "; Author: " + document.get("author")+"; Publication year: " + document.get("year_published") + "<br>";
        }

        return response;
    }


}