package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.datalayer.TranslationDta;
import com.rentmycar.rentmycar.model.Translation;
import com.rentmycar.rentmycar.repository.TranslationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TranslationService {

    private final TranslationRepository translationRepository;
    private ModelMapper modelMapper;

    public TranslationService(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }

    public List<TranslationDta> getTranslation(UUID translationTag) {

        return translationRepository.findTranslationsByTranslationTag(translationTag).stream()
                .map(obj -> modelMapper.map(obj, TranslationDta.class))
                .collect(Collectors.toList());
    }
}
