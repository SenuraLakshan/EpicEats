package com.example.EpicEats.controller;

import com.example.EpicEats.model.Restaurant;
import com.example.EpicEats.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService service;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchNearbyRestaurants(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam(defaultValue = "5.0") double radius
    ) {
        List<Restaurant> restaurants = service.findNearbyRestaurants(latitude, longitude, radius);
        return ResponseEntity.ok(restaurants);
    }


    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long restaurantId) {
        return service.getRestaurantById(restaurantId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant savedRestaurant = service.addRestaurant(restaurant);
        return ResponseEntity.ok(savedRestaurant);
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(
            @PathVariable Long restaurantId,
            @RequestBody Restaurant updatedRestaurant
    ) {
        return ResponseEntity.ok(service.updateRestaurant(restaurantId, updatedRestaurant));
    }
}
