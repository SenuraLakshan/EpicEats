// ReviewService.java
package com.example.usercontent.service;

import com.example.usercontent.model.Review;
import com.example.usercontent.model.Restaurant;
import com.example.usercontent.model.Menu;
import com.example.usercontent.model.User;
import com.example.usercontent.repository.ReviewRepository;
import com.example.usercontent.repository.RestaurantRepository;
import com.example.usercontent.repository.MenuRepository;
import com.example.usercontent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserRepository userRepository;

    public Review createReview(Review review) {
        Review newReview = new Review();
        review.setText(review.getText());
        review.setRating(review.getRating());
        review.setTimestamp(review.getTimestamp());

        // Fetch User, Restaurant, and Menu objects from database using IDs
        Optional<User> userOptional = userRepository.findById(review.getUser().getId());
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(review.getRestaurant().getId());
        Optional<Menu> menuOptional = menuRepository.findById(review.getMenu().getId());

        // Check if User, Restaurant, and Menu exist
        if (userOptional.isPresent() && restaurantOptional.isPresent() && menuOptional.isPresent()) {
            User user = userOptional.get();
            Restaurant restaurant = restaurantOptional.get();
            Menu menu = menuOptional.get();

            // Assign User, Restaurant, and Menu to the review
            newReview.setUser(user);
            newReview.setRestaurant(restaurant);
            newReview.setMenu(menu);

            // Save the review
            return reviewRepository.save(newReview);
        } else {
            throw new RuntimeException("User, Restaurant, or Menu not found");
        }
    }


    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public List<Review> getReviewsByRestaurantId(Long restaurantId) {
        return reviewRepository.findByRestaurantId(restaurantId);
    }

    public List<Review> getReviewsByMenuId(Long menuId) {
        return reviewRepository.findByMenuId(menuId);
    }

    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    public Review updateReview(Long id, Review reviewDetails) {
        return reviewRepository.findById(id)
                .map(review -> {
                    review.setText(reviewDetails.getText());
                    review.setRating(reviewDetails.getRating());
                    review.setTimestamp(reviewDetails.getTimestamp());
                    review.setRestaurant(reviewDetails.getRestaurant());
                    review.setMenu(reviewDetails.getMenu());
                    review.setUser(reviewDetails.getUser());
                    return reviewRepository.save(review);
                }).orElseThrow(() -> new RuntimeException("Review not found with id " + id));
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
