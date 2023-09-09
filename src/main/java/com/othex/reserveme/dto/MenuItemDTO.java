package com.othex.reserveme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDTO {

    private String id;
    private String dishName;
    private String description;
    private long price;
    private String imageUrl;
    private String menuId;
    private String categoriy;
}
