package com.othex.reserveme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.othex.reserveme.entities.MenuItem;

@Component
public interface MenuItemRepository extends JpaRepository<MenuItem, String> {
    List<MenuItem> findByMenu_Id(String menuId);
}
