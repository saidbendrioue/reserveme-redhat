package com.othex.reserveme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.othex.reserveme.entities.Menu;

@Component
public interface MenuRepository extends JpaRepository<Menu, String> {
    List<Menu> findByRestaurant_Id(String restaurantId);
}
