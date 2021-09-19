package com.rentmycar.rentmycar.rentalplan;

import com.rentmycar.rentmycar.car.Car;
import com.rentmycar.rentmycar.insurance.Insurance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
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
    private LocalDateTime available_from;
    private LocalDateTime available_until;
    private BigDecimal price;
    private int distance;
    @OneToOne
    @JoinColumn(nullable = false, name = "insurance_type_id")
    private Insurance insurance_type_id;
    @CreationTimestamp
    private LocalDateTime created_at;
    @UpdateTimestamp
    private LocalDateTime updated_at;
}
