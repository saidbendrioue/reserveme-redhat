package com.othex.reserveme.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.othex.reserveme.entities.Menu;
import com.othex.reserveme.exceptions.TechnicalException;
import com.othex.reserveme.repositories.MenuRepository;
import com.othex.reserveme.repositories.RestaurantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {

    @Value("${image.cdn.url}")
    private String imageCdnUrl;

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;
    private final FileStorageService fileStorageService;

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public Optional<Menu> getMenuById(String id) {
        return menuRepository.findById(id);
    }

    public Menu saveMenu(String restaurantId, Menu menu, MultipartFile file) {
        var restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new TechnicalException(String.format("restaurant %s not found", restaurantId)));

        menu.setRestaurant(restaurant);

        if (file != null) {
            menu.setImageUrl(imageCdnUrl + "/" + fileStorageService.storeFile(file));
        }

        Menu savedMenu = menuRepository.save(menu);

        return savedMenu;
    }

    public void deleteMenu(String id) {
        menuRepository.deleteById(id);
    }

    public List<Menu> getByRestaurantId(String id) {
        return menuRepository.findByRestaurant_Id(id);
    }
}
