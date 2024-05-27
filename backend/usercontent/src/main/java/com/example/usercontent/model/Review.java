package com.example.usercontent.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "\"review\"")  // Escape the table name
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private int rating;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
//    @JsonBackReference
//    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
//    @JsonBackReference
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "menu_id")
//    @JsonBackReference
    private Menu menu;
}