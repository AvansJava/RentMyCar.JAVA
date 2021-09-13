package com.rentmycar.rentmycar.registration.token;

import com.rentmycar.rentmycar.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private LocalDateTime created_at;
    private LocalDateTime expired_at;
    private LocalDateTime confirmed_at;
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public ConfirmationToken(String token, LocalDateTime created_at, LocalDateTime expired_at,
                             User user) {
        this.token = token;
        this.created_at = created_at;
        this.expired_at = expired_at;
        this.user = user;
    }
}
