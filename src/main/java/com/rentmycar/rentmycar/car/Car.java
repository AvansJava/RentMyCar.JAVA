package com.rentmycar.rentmycar.car;

import com.rentmycar.rentmycar.location.Location;
import com.rentmycar.rentmycar.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "car")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private String brandType;
    @ManyToOne
    @JoinColumn(nullable = false, name = "car_type_id")
    private CarType carType;
    private int mileage;
    private int consumption;
    @OneToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(nullable = false, name = "location_id")
    private Location location;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "userCars")
    private Set<User> userCars = new HashSet<>();

    public Set<User> getUserCars() {
        return userCars;
    }
}
