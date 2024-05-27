package com.example.usercontent.controller;

import com.example.usercontent.model.Photo;
import com.example.usercontent.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @PostMapping
    public ResponseEntity<Photo> createPhoto(@RequestBody Photo photo) {
        Photo savedPhoto = photoService.createPhoto(photo);
        return ResponseEntity.ok(savedPhoto);
    }

    @GetMapping
    public ResponseEntity<List<Photo>> getAllPhotos() {
        List<Photo> photos = photoService.getAllPhotos();
        return ResponseEntity.ok(photos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Photo> getPhotoById(@PathVariable Long id) {
        return photoService.getPhotoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Photo> updatePhoto(@PathVariable Long id, @RequestBody Photo photoDetails) {
        Photo updatedPhoto = photoService.updatePhoto(id, photoDetails);
        return ResponseEntity.ok(updatedPhoto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Long id) {
        photoService.deletePhoto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Photo>> getPhotosByRestaurantId(@PathVariable Long restaurantId) {
        List<Photo> photos = photoService.getPhotosByRestaurantId(restaurantId);
        return ResponseEntity.ok(photos);
    }

    @GetMapping("/menu/{menuId}")
    public ResponseEntity<List<Photo>> getPhotosByMenuId(@PathVariable Long menuId) {
        List<Photo> photos = photoService.getPhotosByMenuId(menuId);
        return ResponseEntity.ok(photos);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Photo>> getPhotosByUserId(@PathVariable Long userId) {
        List<Photo> photos = photoService.getPhotosByUserId(userId);
        return ResponseEntity.ok(photos);
    }
}
