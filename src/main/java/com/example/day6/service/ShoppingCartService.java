package com.example.day6.service;

import com.example.day6.model.CartItem;

import java.util.Collection;
import java.util.Map;

public interface ShoppingCartService {
    void addToCart(CartItem cartItem);
    public void addToCartDB(CartItem cartItem);

    void removeFromCart(int carId);

    void removeFromCartDB(int carId);

    CartItem updateCart(int carId, int quantity);


    CartItem updateCartDB(int carId, int quantity);

    void clearCart();

    Map<Integer, CartItem> getCart();

    Map<Integer, CartItem> getCartDB();

    Collection<CartItem> getCartItems();

    Collection<CartItem> getCartItemsDB();

    int getCartSize();

    int getCartSizeDB();

    double getCartTotal();

    double TinhTongTien();

    double getCartTotalDB();

    double tinhTongTienDB();

    void clearCartDB();
}
