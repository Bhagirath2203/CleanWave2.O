package com.cleanwave.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import com.cleanwave.model.Location;

@Data
public class ReportRequest {
    
    @NotBlank(message = "Category is required")
    private String category;
    
    @NotBlank(message = "Description is required")
    @Size(min = 10, message = "Description must be at least 10 characters long")
    private String description;
    
    @NotNull(message = "Location is required")
    private Location location;
    
    private String imageDataUrl;
}
