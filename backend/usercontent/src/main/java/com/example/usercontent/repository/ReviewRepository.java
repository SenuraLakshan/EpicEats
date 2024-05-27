package com.example.usercontent.repository;

import com.example.usercontent.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByRestaurantId(Long restaurantId);
    List<Review> findByMenuId(Long menuId);
    List<Review> findByUserId(Long userId);
}
