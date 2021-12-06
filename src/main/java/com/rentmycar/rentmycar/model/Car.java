package com.rentmycar.rentmycar.model;

import com.rentmycar.rentmycar.enums.CarType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "car")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String brandType;
    private String model;
    @Column(unique = true)
    private String licensePlateNumber;
    private Double consumption; // per 100km
    private int costPrice;
    private String imagePath;
    @Enumerated(EnumType.STRING)
    private CarType carType;
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
