package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.dto.CarList;
import com.rentmycar.rentmycar.dto.TranslationDto;
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

    public TranslationService(TranslationRepository translationRepository, ModelMapper modelMapper) {
        this.translationRepository = translationRepository;
        this.modelMapper = modelMapper;
    }

    public List<TranslationDto> getTranslation(UUID translationTag) {

        return translationRepository.findTranslationsByTranslationTag(translationTag).stream()
                .map(obj -> modelMapper.map(obj, TranslationDto.class))
                .collect(Collectors.toList());
    }

    public List<TranslationDto> getTranslationByTranslationTag(UUID translationTag) {
        return translationRepository.findTranslationsByTranslationTag(translationTag).stream()
                .map(obj -> modelMapper.map(obj, TranslationDto.class))
                .collect(Collectors.toList());
    }
}
