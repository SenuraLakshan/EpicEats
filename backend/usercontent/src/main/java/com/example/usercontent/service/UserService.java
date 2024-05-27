package com.example.usercontent.service;

import com.example.usercontent.model.User;
import com.example.usercontent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        try{
            // Hash the password before saving
            //user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setPassword(user.getPassword());
            return userRepository.save(user);
        }
        catch(Exception exception){
            System.out.println("error");
            return user;
        }

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userDetails.getUsername());
                    // Hash the new password before updating
                    //user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
                    user.setPassword(userDetails.getPassword());
                    return userRepository.save(user);
                }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
