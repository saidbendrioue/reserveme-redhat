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
import com.othex.reserveme.dto.RestaurantDTO;
import com.othex.reserveme.entities.Restaurant;
import com.othex.reserveme.mappers.RestaurantMapper;
import com.othex.reserveme.services.RestaurantService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final ObjectMapper objectMapper;
    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    @GetMapping
    public List<RestaurantDTO> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return restaurantMapper.toDtos(restaurants);
    }

    @GetMapping("/{id}")
    public RestaurantDTO getRestaurantById(@PathVariable String id) {
        Restaurant restaurants = restaurantService.getRestaurantById(id);
        return restaurantMapper.toDto(restaurants);
    }

    @PostMapping
    public RestaurantDTO createRestaurant(
            @RequestParam("restaurantData") String restaurantData,
            @RequestParam(value = "file", required = false) MultipartFile file)
            throws JsonMappingException, JsonProcessingException {
        var restaurantDTO = objectMapper.readValue(restaurantData, RestaurantDTO.class);
        Restaurant restaurant = restaurantMapper.toDao(restaurantDTO);
        Restaurant savedRestaurant = restaurantService.saveRestaurant(restaurantDTO.getOwnerId(), restaurant, file);
        return restaurantMapper.toDto(savedRestaurant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDTO> updateRestaurant(
            @PathVariable String id,
            @RequestParam("restaurantData") String restaurantData,
            @RequestParam("ownerId") String ownerId,
            @RequestParam(value = "file", required = false) MultipartFile file)
            throws JsonMappingException, JsonProcessingException {

        Restaurant restaurant = objectMapper.readValue(restaurantData, Restaurant.class);
        restaurant.setId(id);
        Restaurant savedRestaurant = restaurantService.saveRestaurant(ownerId, restaurant, file);
        return ResponseEntity.ok(restaurantMapper.toDto(savedRestaurant));
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable String id) {
        restaurantService.deleteRestaurant(id);
    }

    @GetMapping("/getByOwnerId/{ownerId}")
    public List<RestaurantDTO> getRestaurantsByOwnerId(@PathVariable String ownerId) {
        List<Restaurant> restaurants = restaurantService.getRestaurantsByOwnerId(ownerId);
        return restaurantMapper.toDtos(restaurants);
    }
}
