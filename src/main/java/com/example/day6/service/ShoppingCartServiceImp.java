package com.example.day6.service;

import com.example.day6.model.CartItem;
import com.example.day6.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SessionScope
@Service
public class ShoppingCartServiceImp implements ShoppingCartService{
//    Key là code định danh duy nhất được sử dụng để liên kết từng phần tử (value)
//    trong map
//Value là các phần tử được liên kết bởi các key trong map
    Map<Integer, CartItem> cart= new HashMap<>();
    @Autowired
    CartRepository cartItemRepository;
   @Override
//   khi không dùng db
//    thêm 1 sản phẩm vào giỏ hàng số lượng mặc định là 1
//    khi map chưa có carId tức là chưa có sản phẩm trong giỏ hàng
//    put 1 sản phẩm vào giỏ hàng
//    khi map đã có carId tức là đã có sản phẩm trong giỏ hàng
//    tức là lấy ra id của sản phẩm đó được thì số lượng của sản phẩm đó
//    trong giỏ hàng tăng lên 1
    public void addToCart(CartItem cartItem){
        if(cart.containsKey(cartItem.getCarId())){
            CartItem item= cart.get(cartItem.getCarId());
            item.setQuantity(item.getQuantity()+1);
        }else{
            cart.put(cartItem.getCarId(), cartItem);
        }
    }
    @Override
//    hàm này dùng để thêm sản phẩm vào giỏ hàng khi dùng db
    public void addToCartDB(CartItem cartItem) {
        CartItem existingItem = cartItemRepository.findByCarId(cartItem.getCarId());

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + 1);
            cartItemRepository.save(existingItem);
        } else {
            cartItemRepository.save(cartItem);
        }
    }
    @Override
//    xóa 1 sản phẩm trong giỏ hàng khi không dùng db mà dùng map
    public void removeFromCart(int carId){
        cart.remove(carId);
    }
@Override
//    xóa 1 sản phẩm trong giỏ hàng khi dùng db
    public void removeFromCartDB(int carId) {
        cartItemRepository.deleteById(carId);
    }
    @Override
//    cập nhật số lượng sản phẩm trong giỏ hàng khi không dùng db
//     tìm sản phẩm theo id nếu tìm thấy sản phẩm trong giỏ hàng thì lấy ra sản phẩm đó
//     và cập nhật số lượng
    public CartItem updateCart(int carId, int quantity){
        CartItem item= cart.get(carId);
        item.setQuantity(quantity);
        return item;
    }
    @Override
//    cập nhật số lượng sản phẩm trong giỏ hàng khi dùng db
    public CartItem updateCartDB(int carId, int quantity) {
        Optional<CartItem> existingItem = cartItemRepository.findById(carId);

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(quantity);
            return cartItemRepository.save(item);
        }

        return null;
    }
    @Override
//    xóa toàn bộ giỏ hàng khi không dùng db
    public void clearCart(){
        cart.clear();
    }
//    xóa toàn bộ giỏ hàng khi dùng db
    @Autowired
    public void clearCartDB() {
        cartItemRepository.deleteAll();
    }

    @Override
//    lấy ra danh sách sản phẩm trong giỏ hàng khi không dùng db trả về 1 map(key, value)
    public Map<Integer, CartItem> getCart(){
        return cart;
    }
    @Override
//    lấy ra danh sách sản phẩm trong giỏ hàng khi dùng db trả về 1 collection (value)
    public Collection<CartItem> getCartItems(){
        return cart.values();
    }
    @Override
//    lấy ra số lượng sản phẩm trong giỏ hàng khi không dùng db
    public int getCartSize(){
        return cart.size();
    }
    @Override
//    tính tổng tiền của giỏ hàng khi không dùng db
    public double getCartTotal(){
        double total=0;
        for(CartItem item: cart.values()){
            total+=item.getPrice()*item.getQuantity();
        }
        return total;
    }
    @Override
//    tính tổng tiền của giỏ hàng khi
//    không dùng db có áp dụng stream, mapToDouble, sum, lambda
    public double TinhTongTien(){
        return cart.values().stream()
                .mapToDouble(item->item.getPrice()*item.getQuantity()).sum();
    }

//các hàm bên duoi dùng db
@Override
    public Map<Integer, CartItem> getCartDB() {
        Map<Integer, CartItem> cart = new HashMap<>();
        cartItemRepository.findAll().forEach(item -> cart.put(item.getCarId(), item));
        return cart;
    }
@Override
    public Collection<CartItem> getCartItemsDB() {
        return cartItemRepository.findAll();
    }
    @Override
    public int getCartSizeDB() {
        return (int) cartItemRepository.count();
    }
    @Override
    public double getCartTotalDB() {
        double total = 0;
        for (CartItem item : getCartItems()) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
@Override
    public double tinhTongTienDB() {
        return getCartItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
    }
}
