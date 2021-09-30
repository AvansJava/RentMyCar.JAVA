package com.rentmycar.rentmycar.cartimeslotavailability;

import com.rentmycar.rentmycar.car.Car;
import com.rentmycar.rentmycar.product.Product;
import com.rentmycar.rentmycar.timeslot.Timeslot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "car_timeslot_availability")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarTimeslotAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false, name = "car_id")
    private Car car;
    @ManyToOne
    @JoinColumn(nullable = false, name = "timeslot_id")
    private Timeslot timeslot;
    @OneToOne
    @JoinColumn(nullable = false, name = "product_id")
    private Product product;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
