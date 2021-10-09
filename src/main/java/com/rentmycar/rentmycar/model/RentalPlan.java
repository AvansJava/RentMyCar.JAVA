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
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    private LocalDateTime availableFrom;
    private LocalDateTime availableUntil;
    private BigDecimal price;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
