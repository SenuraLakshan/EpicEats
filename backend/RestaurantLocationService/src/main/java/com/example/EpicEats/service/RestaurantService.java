package com.example.EpicEats.service;

import com.example.EpicEats.exception.ResourceNotFoundException;
import com.example.EpicEats.model.Restaurant;
import com.example.EpicEats.repository.RestaurantRepository;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository repository;
    private Firestore db = FirestoreClient.getFirestore();

    public List<Restaurant> findNearbyRestaurants(double latitude, double longitude, double radius) throws ExecutionException, InterruptedException {
        double latDiff = radius / 111; // 1 degree latitude ~= 111 km
        double lonDiff = radius / (111 * Math.cos(Math.toRadians(latitude))); // Adjust for longitude

        return repository.findByLatitudeBetweenAndLongitudeBetween(
                latitude - latDiff, latitude + latDiff,
                longitude - lonDiff, longitude + lonDiff
        );
    }

    public Optional<Restaurant> getRestaurantById(String id) throws ExecutionException, InterruptedException {
        return Optional.ofNullable(repository.findById(id));
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        if (restaurant.getId() == null) {
            restaurant.setId(db.collection("restaurants").document().getId());
        }
        db.collection("restaurants").document(restaurant.getId()).set(restaurant);
        return restaurant;
    }

    public Restaurant updateRestaurant(String id, Restaurant updatedRestaurant) throws ExecutionException, InterruptedException {
        Restaurant restaurant = repository.findById(id);
        if (restaurant != null) {
            restaurant.setName(updatedRestaurant.getName());
            restaurant.setAddress(updatedRestaurant.getAddress());
            restaurant.setLatitude(updatedRestaurant.getLatitude());
            restaurant.setLongitude(updatedRestaurant.getLongitude());
            return repository.save(restaurant);
        } else {
            throw new ResourceNotFoundException("Restaurant not found with id " + id);
        }
    }
}
