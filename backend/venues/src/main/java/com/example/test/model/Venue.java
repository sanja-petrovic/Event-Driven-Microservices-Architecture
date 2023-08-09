package com.example.test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "venues")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Venue {
    @Id
    private UUID id = UUID.randomUUID();
    @Column
    private String name;
    @Column
    private String location;
    @Column
    @CreationTimestamp
    private LocalDateTime created;
    @Column
    @UpdateTimestamp
    private LocalDateTime lastUpdated;
}
