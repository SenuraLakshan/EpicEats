package com.example.EpicEats.repository;

import com.example.EpicEats.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByLatitudeBetweenAndLongitudeBetween(
            double minLat, double maxLat, double minLon, double maxLon
    );
}
