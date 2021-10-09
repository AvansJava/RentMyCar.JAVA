package com.rentmycar.rentmycar.controller;

import com.rentmycar.rentmycar.dto.TranslationDto;
import com.rentmycar.rentmycar.service.TranslationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="api/v1.0/translation/")
public class TranslationController {

    private final TranslationService translationService;

    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @GetMapping(path = "{translationTag}/")
    public List<TranslationDto> getLocation(@PathVariable("translationTag") UUID translationTag) {
        return translationService.getTranslationByTranslationTag(translationTag);
    }
}
