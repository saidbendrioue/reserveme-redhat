package com.othex.reserveme.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.othex.reserveme.dto.MenuItemDTO;
import com.othex.reserveme.entities.MenuItem;
import com.othex.reserveme.mappers.MenuItemMapper;
import com.othex.reserveme.services.MenuItemService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/menu-items")
@RequiredArgsConstructor
public class MenuItemController {

    private final ObjectMapper objectMapper;
    private final MenuItemService menuItemService;
    private final MenuItemMapper menuItemMapper;

    @GetMapping
    public List<MenuItemDTO> getAllMenuItems() {
        List<MenuItem> menus = menuItemService.getAllMenuItems();
        return menuItemMapper.toDtos(menus);
    }

    @PostMapping
    public MenuItemDTO createMenuItem(
            @RequestParam("menuItemData") String menuItemData,
            @RequestParam(value = "file", required = false) MultipartFile file)
            throws JsonMappingException, JsonProcessingException {
        var menuItemDTO = objectMapper.readValue(menuItemData, MenuItemDTO.class);
        MenuItem menuItem = menuItemMapper.toDao(menuItemDTO);
        var savedMenuItem = menuItemService.saveMenuItem(menuItemDTO.getMenuId(), menuItem, file);
        return menuItemMapper.toDto(savedMenuItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItemDTO> updateMenuItem(
            @PathVariable String id,
            @RequestParam("menuItemData") String menuItemData,
            @RequestParam("menuId") String menuId,
            @RequestParam(value = "file", required = false) MultipartFile file)
            throws JsonMappingException, JsonProcessingException {
        if (!menuItemService.getMenuItemById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        MenuItem menu = objectMapper.readValue(menuItemData, MenuItem.class);
        menu.setId(id);
        MenuItem savedMenuItem = menuItemService.saveMenuItem(menuId, menu, file);
        return ResponseEntity.ok(menuItemMapper.toDto(savedMenuItem));
    }

    @DeleteMapping("/{id}")
    public void deleteMenuItem(@PathVariable String id) {
        menuItemService.deleteMenuItem(id);
    }

    @GetMapping("/{menuId}")
    public List<MenuItemDTO> getMenuItemsByMenuId(@PathVariable String menuId) {
        List<MenuItem> menuItems = menuItemService.getMenuItemsByMenuId(menuId);
        return menuItemMapper.toDtos(menuItems);
    }
}
