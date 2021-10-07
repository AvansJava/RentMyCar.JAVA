package com.rentmycar.rentmycar.repository;

import com.rentmycar.rentmycar.model.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, Long> {
    Optional<Translation> findTranslationsByTranslationTag(UUID translationTag);
}
