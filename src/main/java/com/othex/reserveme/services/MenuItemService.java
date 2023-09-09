package com.othex.reserveme.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.othex.reserveme.entities.MenuItem;
import com.othex.reserveme.exceptions.TechnicalException;
import com.othex.reserveme.repositories.MenuItemRepository;
import com.othex.reserveme.repositories.MenuRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    @Value("${image.cdn.url}")
    private String imageCdnUrl;

    private final FileStorageService fileStorageService;
    private final MenuItemRepository menuItemRepository;
    private final MenuRepository menuRepository;

    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    public Optional<MenuItem> getMenuItemById(String id) {
        return menuItemRepository.findById(id);
    }

    public MenuItem saveMenuItem(String menuId, MenuItem menuItem, MultipartFile file) {
        var menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new TechnicalException(String.format("menu %s not found", menuId)));

        menuItem.setMenu(menu);

        if (file != null) {
            menuItem.setImageUrl(imageCdnUrl + "/" + fileStorageService.storeFile(file));
        }

        var savedMenu = menuItemRepository.save(menuItem);

        return savedMenu;
    }

    public void deleteMenuItem(String id) {
        menuItemRepository.deleteById(id);
    }

    public List<MenuItem> getMenuItemsByMenuId(String menuId) {
        return menuItemRepository.findByMenu_Id(menuId);
    }
}
