package com.rentmycar.rentmycar.model;

import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.model.Insurance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "rental_plan")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RentalPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false, name = "car_id")
    private Car car;
    private LocalDateTime availableFrom;
    private LocalDateTime availableUntil;
    private BigDecimal price;
    private int distance;
    @OneToOne
    @JoinColumn(nullable = false, name = "insurance_type_id")
    private Insurance insuranceTypeId;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
