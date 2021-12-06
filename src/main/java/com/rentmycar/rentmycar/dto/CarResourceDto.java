package com.rentmycar.rentmycar.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CarResourceDto {
    private Long id;
    private String filePath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
