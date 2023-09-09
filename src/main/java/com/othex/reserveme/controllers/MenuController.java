package com.othex.reserveme.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.othex.reserveme.dto.MenuDTO;
import com.othex.reserveme.entities.Menu;
import com.othex.reserveme.mappers.MenuMapper;
import com.othex.reserveme.services.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;
    private final ObjectMapper objectMapper;
    private final MenuMapper menuMapper;

    @GetMapping
    public List<MenuDTO> getAllMenus() {
        List<Menu> menus = menuService.getAllMenus();
        return menuMapper.toDtos(menus);
    }
    @GetMapping("/getByRestaurantId/{id}")
    public List<MenuDTO> getMenusByRestaurantId(@PathVariable String id) {
        List<Menu> menus = menuService.getByRestaurantId(id);
        return menuMapper.toDtos(menus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDTO> getMenuById(@PathVariable String id) {
        Optional<Menu> menu = menuService.getMenuById(id);
        return menu.map(menuMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MenuDTO createMenu(
            @RequestParam("menuData") String menuData,
            @RequestParam(value = "file", required = false) MultipartFile file)
            throws JsonMappingException, JsonProcessingException {

        var menuDTO = objectMapper.readValue(menuData, MenuDTO.class);
        var menu = menuMapper.toDao(menuDTO);
        var savedMenu = menuService.saveMenu(menuDTO.getRestaurantId(), menu, file);
        return menuMapper.toDto(savedMenu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuDTO> updateMenu(
            @PathVariable String id,
            @RequestParam("menuData") String menuData,
            @RequestParam(value = "file", required = false) MultipartFile file)
            throws JsonMappingException, JsonProcessingException {
        var existingMenu = menuService.getMenuById(id);
        if (existingMenu.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var menuDTO = objectMapper.readValue(menuData, MenuDTO.class);
        menuDTO.setId(id);
        var menu = menuMapper.toDao(menuDTO);
        Menu savedMenu = menuService.saveMenu(menuDTO.getRestaurantId(), menu, file);
        return ResponseEntity.ok(menuMapper.toDto(savedMenu));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable String id) {
        Optional<Menu> existingMenu = menuService.getMenuById(id);
        if (existingMenu.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        menuService.deleteMenu(id);
        return ResponseEntity.noContent().build();
    }
}
