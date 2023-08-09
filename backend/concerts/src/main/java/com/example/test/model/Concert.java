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
@Table(name = "concerts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Concert {
    @Id
    private UUID id = UUID.randomUUID();
    @Column
    private String name;
    @Column
    private UUID venueId;
    @Column
    private String performer;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTime;
    @Column
    private int attendance;
    @Column
    @CreationTimestamp
    private LocalDateTime created;
    @Column
    @UpdateTimestamp
    private LocalDateTime lastUpdated;
}
