// MenuService.java
package com.example.usercontent.service;

import com.example.usercontent.model.Menu;
import com.example.usercontent.model.Restaurant;
import com.example.usercontent.repository.MenuRepository;
import com.example.usercontent.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Menu createMenu(Menu menu) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(menu.getRestaurant().getId());
        if (restaurant.isPresent()) {
            menu.setRestaurant(restaurant.get());
            return menuRepository.save(menu);
        } else {
            throw new RuntimeException("Restaurant not found with id " + menu.getRestaurant().getId());
        }
    }

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public Optional<Menu> getMenuById(Long id) {
        return menuRepository.findById(id);
    }

    public Menu updateMenu(Long id, Menu menuDetails) {
        return menuRepository.findById(id)
                .map(menu -> {
                    menu.setName(menuDetails.getName());
                    Optional<Restaurant> restaurant = restaurantRepository.findById(menuDetails.getRestaurant().getId());
                    if (restaurant.isPresent()) {
                        menu.setRestaurant(restaurant.get());
                    } else {
                        throw new RuntimeException("Restaurant not found with id " + menuDetails.getRestaurant().getId());
                    }
                    return menuRepository.save(menu);
                }).orElseThrow(() -> new RuntimeException("Menu not found with id " + id));
    }

    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }
}