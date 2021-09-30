package com.rentmycar.rentmycar.insurance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "insurance")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Insurance {
    @Id
    private String insuranceTypeId;
    private String description;
    private BigDecimal price;
}
