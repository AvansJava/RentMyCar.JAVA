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
@Table(name = "car_resource")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filePath;
    @ManyToOne
    @JoinColumn(nullable = false, name = "car_id")
    private Car car;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public CarResource(String filePath, Car car) {
        this.filePath = filePath;
        this.car = car;
    }
}
