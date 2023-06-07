package com.example.day6.controller;

import com.example.day6.model.CartItem;
import com.example.day6.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


    @RestController
    @RequestMapping("/cart")
    public class CartController {
        private final ShoppingCartService cartService;

        @Autowired
        public CartController(ShoppingCartService cartService) {
            this.cartService = cartService;
        }

        @PostMapping("/add")
        public void addToCart(@RequestBody CartItem cartItem) {
            cartService.addToCartDB(cartItem);
        }

        @DeleteMapping("/remove/{carId}")
        public void removeFromCart(@PathVariable int carId) {
            cartService.removeFromCartDB(carId);
        }

        @PutMapping("/update/{carId}/{quantity}")
        public CartItem updateCart(@PathVariable int carId, @PathVariable int quantity) {
            return cartService.updateCartDB(carId, quantity);
        }

        @DeleteMapping("/clear")
        public void clearCart() {
            cartService.clearCartDB();
        }

        @GetMapping("/items")
        public Collection<CartItem> getCartItems() {
            return cartService.getCartItemsDB();
        }

        @GetMapping("/size")
        public int getCartSize() {
            return cartService.getCartSizeDB();
        }

        @GetMapping("/total")
        public double getCartTotal() {
            return cartService.getCartTotalDB();
        }



    }
