package com.rentmycar.rentmycar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "translation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Translation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID translationTag;
    private String language;
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
}
