package com.example.day6.repository;

import com.example.day6.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartItem, Integer> {

    CartItem findByCarId(int carId);
}
