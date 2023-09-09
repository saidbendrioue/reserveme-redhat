package com.othex.reserveme.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.othex.reserveme.dto.MenuDTO;
import com.othex.reserveme.entities.Menu;

@Mapper(componentModel="spring")
public interface MenuMapper {
    MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);

    @Mapping(source = "restaurant.id", target = "restaurantId")
    MenuDTO toDto(Menu menu);

    @Mapping(source = "restaurant.id", target = "restaurantId")
    List<MenuDTO> toDtos(List<Menu> menus);

    Menu toDao(MenuDTO menuDTO);
}
