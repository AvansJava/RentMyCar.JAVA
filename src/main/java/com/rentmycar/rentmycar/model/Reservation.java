package com.rentmycar.rentmycar.model;

import com.rentmycar.rentmycar.enums.ReservationStatus;
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
@Table(name = "reservation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
    @Id
    @Column(columnDefinition = "char(10)")
    private String reservationNumber;
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    private LocalDateTime paidAt;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
