package com.example.EpicEats.service;

import com.example.EpicEats.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import com.example.EpicEats.model.Restaurant;
import com.example.EpicEats.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository repository;

    public List<Restaurant> findNearbyRestaurants(double latitude, double longitude, double radius) {
        double latDiff = radius / 111; // 1 degree latitude ~= 111 km
        double lonDiff = radius / (111 * Math.cos(Math.toRadians(latitude))); // Adjust for longitude

        return repository.findByLatitudeBetweenAndLongitudeBetween(
                latitude - latDiff, latitude + latDiff,
                longitude - lonDiff, longitude + lonDiff
        );
    }

    public Optional<Restaurant> getRestaurantById(Long id) {
        return repository.findById(id);
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public Restaurant updateRestaurant(Long id, Restaurant updatedRestaurant) {
        return repository.findById(id).map(restaurant -> {
            restaurant.setName(updatedRestaurant.getName());
            restaurant.setAddress(updatedRestaurant.getAddress());
            restaurant.setLatitude(updatedRestaurant.getLatitude());
            restaurant.setLongitude(updatedRestaurant.getLongitude());
            return repository.save(restaurant);
        }).orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id " + id));
    }

}
