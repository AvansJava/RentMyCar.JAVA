package com.rentmycar.rentmycar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "driver_activity")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DriverActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false, name = "car_id")
    private Car car;
    @ManyToOne
    @JoinColumn(nullable = false, name = "reservation_number")
    private Reservation reservation;
    private int distanceDriven;
    private int topSpeed;
    private int averageSpeed;
    private int acceleration;
    private int brake;
    private LocalDateTime lastSyncedAt;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
