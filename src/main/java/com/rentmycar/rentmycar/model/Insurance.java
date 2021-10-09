package com.rentmycar.rentmycar.model;

import com.rentmycar.rentmycar.dto.TranslationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "insurance")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Insurance {
    @Id
    private String insuranceTypeId;
    private String nameTranslationTag;
    private UUID descriptionTranslationTag;
    private BigDecimal price;
}
