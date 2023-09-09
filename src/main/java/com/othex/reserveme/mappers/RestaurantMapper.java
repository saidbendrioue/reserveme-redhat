package com.othex.reserveme.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.othex.reserveme.dto.RestaurantDTO;
import com.othex.reserveme.entities.Restaurant;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    @Mapping(source = "owner.id", target = "ownerId")
    RestaurantDTO toDto(Restaurant restaurant);

    @Mapping(source = "owner.id", target = "ownerId")
    List<RestaurantDTO> toDtos(List<Restaurant> restaurants);

    Restaurant toDao(RestaurantDTO restaurantDTO);
}
