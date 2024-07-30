package com.example.EpicEats.repository;

import com.example.EpicEats.model.Restaurant;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class RestaurantRepository {

    private static final String COLLECTION_NAME = "restaurants";

    public List<Restaurant> findAll() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Restaurant> restaurants = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            restaurants.add(document.toObject(Restaurant.class));
        }
        return restaurants;
    }

    public Restaurant findById(String id) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        return document.exists() ? document.toObject(Restaurant.class) : null;
    }

    public Restaurant save(Restaurant restaurant) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future = dbFirestore.collection(COLLECTION_NAME).document(restaurant.getId().toString()).set(restaurant);
        future.get();
        return restaurant;
    }

    public void deleteById(String id) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future = dbFirestore.collection(COLLECTION_NAME).document(id).delete();
        future.get();
    }

    public List<Restaurant> findByLatitudeBetweenAndLongitudeBetween(double minLat, double maxLat, double minLon, double maxLon) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference restaurants = dbFirestore.collection(COLLECTION_NAME);
        Query query = restaurants
                .whereGreaterThanOrEqualTo("latitude", minLat)
                .whereLessThanOrEqualTo("latitude", maxLat)
                .whereGreaterThanOrEqualTo("longitude", minLon)
                .whereLessThanOrEqualTo("longitude", maxLon);

        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Restaurant> restaurantList = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            restaurantList.add(document.toObject(Restaurant.class));
        }
        return restaurantList;
    }
}
