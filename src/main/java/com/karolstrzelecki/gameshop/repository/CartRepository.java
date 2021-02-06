package com.karolstrzelecki.gameshop.repository;

import com.karolstrzelecki.gameshop.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<ShoppingCart, Long > {
}
