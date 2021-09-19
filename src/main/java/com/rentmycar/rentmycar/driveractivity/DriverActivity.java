package com.rentmycar.rentmycar.driveractivity;

import com.rentmycar.rentmycar.car.Car;
import com.rentmycar.rentmycar.reservation.Reservation;
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
    private int distance_driven;
    private int top_speed;
    private int average_speed;
    private int acceleration;
    private int brake;
    private LocalDateTime last_synced_at;
    @CreationTimestamp
    private LocalDateTime created_at;
    @UpdateTimestamp
    private LocalDateTime updated_at;
}
