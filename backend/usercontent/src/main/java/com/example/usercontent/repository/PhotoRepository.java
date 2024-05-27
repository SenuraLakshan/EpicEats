package com.example.usercontent.repository;

import com.example.usercontent.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByRestaurantId(Long restaurantId);
    List<Photo> findByMenuId(Long menuId);
    List<Photo> findByUserId(Long userId);
}
