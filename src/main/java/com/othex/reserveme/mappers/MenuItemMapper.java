package com.othex.reserveme.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.othex.reserveme.dto.MenuItemDTO;
import com.othex.reserveme.entities.MenuItem;

@Mapper(componentModel="spring")
public interface MenuItemMapper {
    MenuItemMapper INSTANCE = Mappers.getMapper(MenuItemMapper.class);

    @Mapping(source = "menu.id", target = "menuId")
    MenuItemDTO toDto(MenuItem menuItem);

    @Mapping(source = "menu.id", target = "menuId")
    List<MenuItemDTO> toDtos(List<MenuItem> menuItems);

    MenuItem toDao(MenuItemDTO menuItemDTO);
}