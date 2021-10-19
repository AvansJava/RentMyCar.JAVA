package com.rentmycar.rentmycar.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "location")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String street;
    @NotNull
    private String houseNumber;
    @NotNull
    private String postalCode;
    @NotNull
    private String city;
    @NotNull
    private String country;
    @Column(precision=10, scale=6)
    @NotNull
    private Float latitude;
    @Column(precision=10, scale=6)
    @NotNull
    private Float longitude;
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Location(String street, String houseNumber, String postalCode, String city, String country, Float latitude, Float longitude, User user) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.user = user;
    }
}
