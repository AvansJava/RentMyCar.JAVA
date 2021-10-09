package com.rentmycar.rentmycar.model;

import com.rentmycar.rentmycar.enums.ProductStatus;
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
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false, name = "reservation_number")
    private Reservation reservation;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(nullable = false, name = "rental_plan_id")
    private RentalPlan rentalPlan;
    @OneToOne
    @JoinColumn(nullable = false, name = "insurance_type_id")
    private Insurance insuranceTypeId;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
