package com.rentmycar.rentmycar.model;

import com.rentmycar.rentmycar.enums.TimeSlotAvailabilityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.http.ResponseEntity;

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
    @JoinColumn(name = "product_id")
    private Product product;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    @Enumerated(EnumType.STRING)
    private TimeSlotAvailabilityStatus status;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
