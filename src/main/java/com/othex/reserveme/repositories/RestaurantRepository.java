package com.othex.reserveme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.othex.reserveme.entities.Restaurant;

@Component
public interface RestaurantRepository extends JpaRepository<Restaurant, String> {

    List<Restaurant> findByOwner_Id(String ownerId);

}
