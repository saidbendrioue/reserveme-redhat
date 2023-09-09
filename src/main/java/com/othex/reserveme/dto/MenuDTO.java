package com.othex.reserveme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {

    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private String restaurantId;
    private String templateId;
}
