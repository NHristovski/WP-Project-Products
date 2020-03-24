package hristovski.nikola.product.service;

import hristovski.nikola.product.exception.MaxQuantityReachedException;
import hristovski.nikola.product.exception.MinQuantityReachedException;
import hristovski.nikola.product.model.ShoppingCartItem;

public interface ShoppingCartItemService {

    ShoppingCartItem getShoppingCardItem(Long id);
    ShoppingCartItem addShoppingCardItem(ShoppingCartItem shoppingCartItem);

    ShoppingCartItem editShoppingCardItem(ShoppingCartItem shoppingCartItem);
    void deleteShoppingCardItem(long shoppingCartItemId);

    void incrementQuantity(Long id) throws MaxQuantityReachedException;
    void decrementQuantity(Long id) throws MinQuantityReachedException;
}
