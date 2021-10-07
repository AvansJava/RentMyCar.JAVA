package com.rentmycar.rentmycar.repository;

import com.rentmycar.rentmycar.dto.TranslationDto;
import com.rentmycar.rentmycar.model.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, Long> {

    List<Translation> findTranslationsByTranslationTag(UUID translationTag);
}
