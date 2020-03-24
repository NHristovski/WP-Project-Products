package hristovski.nikola.product.service;

import hristovski.nikola.product.model.CardData;

public interface PurchaseService {

    void buyItems(long shoppingCartId, CardData cardData);
}
