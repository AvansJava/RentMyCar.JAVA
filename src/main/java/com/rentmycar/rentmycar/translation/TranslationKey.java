package com.rentmycar.rentmycar.translation;

import java.io.Serializable;
import java.util.UUID;

public class TranslationKey implements Serializable {
    private UUID translationTag;
    private String language;
}
