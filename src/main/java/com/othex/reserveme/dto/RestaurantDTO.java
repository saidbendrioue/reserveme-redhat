package com.othex.reserveme.dto;

import lombok.Data;

@Data
public class RestaurantDTO {

    private String id;
    private String name;
    private String description;
    private String type;
    private String email;
    private String phone;
    private String address;
    private String imageUrl;
    private String ownerId;
}
