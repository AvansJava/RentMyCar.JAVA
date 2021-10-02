package com.rentmycar.rentmycar.translation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "translation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(TranslationKey.class)
public class Translation {
    @Id
    private UUID translationTag;
    @Id
    private String language;
    @Lob
    private String content;
}
