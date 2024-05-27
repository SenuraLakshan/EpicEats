package com.example.usercontent.service;

import com.example.usercontent.model.Photo;
import com.example.usercontent.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public Photo createPhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public Optional<Photo> getPhotoById(Long id) {
        return photoRepository.findById(id);
    }

    public Photo updatePhoto(Long id, Photo photoDetails) {
        return photoRepository.findById(id)
                .map(photo -> {
                    photo.setPhotoUrl(photoDetails.getPhotoUrl());
                    photo.setRestaurant(photoDetails.getRestaurant());
                    photo.setMenu(photoDetails.getMenu());
                    // Update other fields if needed
                    return photoRepository.save(photo);
                }).orElseThrow(() -> new RuntimeException("Photo not found with id " + id));
    }

    public void deletePhoto(Long id) {
        photoRepository.deleteById(id);
    }

    public List<Photo> getPhotosByRestaurantId(Long restaurantId) {
        return photoRepository.findByRestaurantId(restaurantId);
    }

    public List<Photo> getPhotosByMenuId(Long menuId) {
        return photoRepository.findByMenuId(menuId);
    }

    public List<Photo> getPhotosByUserId(Long userId) {
        return photoRepository.findByUserId(userId);
    }
}
