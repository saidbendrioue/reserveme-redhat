package com.othex.reserveme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.othex.reserveme.entities.Restaurant;
import com.othex.reserveme.exceptions.TechnicalException;
import com.othex.reserveme.repositories.RestaurantRepository;
import com.othex.reserveme.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    @Value("${image.cdn.url}")
    private String imageCdnUrl;

    private final FileStorageService fileStorageService;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository UserRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant saveRestaurant(String ownerId, Restaurant restaurant, MultipartFile file) {
        var owner = UserRepository.findById(ownerId)
                .orElseThrow(() -> new TechnicalException(String.format("Owner %s not found", ownerId)));

        restaurant.setOwner(owner);

        if(file != null){
            restaurant.setImageUrl(imageCdnUrl + "/" + fileStorageService.storeFile(file));
        }

        var savedRestaurant = restaurantRepository.save(restaurant);

        return savedRestaurant;
    }

    public void deleteRestaurant(String id) {
        try {
            restaurantRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new TechnicalException("Restaurant not found");
        }
    }

    public List<Restaurant> getRestaurantsByOwnerId(String ownerId) {
        return restaurantRepository.findByOwner_Id(ownerId);
    }

    public Restaurant getRestaurantById(String id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new TechnicalException("Restaurant not found"));
    }
}
