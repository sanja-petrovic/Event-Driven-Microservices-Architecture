package com.example.test.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    private UUID id = UUID.randomUUID();
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String phone;
    @Column
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Column
    private boolean active = false;
    @Column
    @CreationTimestamp
    private LocalDateTime created;
    @Column
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

}
