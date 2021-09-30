package com.rentmycar.rentmycar.reservation;

import com.rentmycar.rentmycar.user.User;
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
    private String reservationNumber;
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime paidAt;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
