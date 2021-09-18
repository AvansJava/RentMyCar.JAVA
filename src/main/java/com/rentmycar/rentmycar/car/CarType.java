package com.rentmycar.rentmycar.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "car_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarType {
    @Id
    private String car_type_id;
    private String description;
}
