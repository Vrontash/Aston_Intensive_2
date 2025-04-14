package org.example.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private int age;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public User(String name, String email, int age){
        this.name = name;
        this.email = email;
        this.age = age;
        this.createdAt = LocalDateTime.now();
    }
}
