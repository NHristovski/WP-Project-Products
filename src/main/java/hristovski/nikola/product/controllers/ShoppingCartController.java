package hristovski.nikola.product.controllers;

import hristovski.nikola.product.exception.*;
import hristovski.nikola.product.model.ShoppingCart;
import hristovski.nikola.product.model.shoppingCart.AddProductToShoppingCartRequest;
import hristovski.nikola.product.model.shoppingCart.BuyRequest;
import hristovski.nikola.product.model.shoppingCart.QuantityRequest;
import hristovski.nikola.product.service.ShoppingCartItemService;
import hristovski.nikola.product.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/shoppingCart")
@RequiredArgsConstructor
@Slf4j
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartItemService shoppingCartItemService;

    @GetMapping("/{username}")
    public ResponseEntity<ShoppingCart> getShoppingCardWithPendingItems(@PathVariable String username) {
        return ResponseEntity.ok(shoppingCartService.getShoppingCartWithPendingItems(username));
    }

    @GetMapping("/history/{username}")
    public ResponseEntity<ShoppingCart> getShoppingCardHistory(@PathVariable String username) {
        return ResponseEntity.ok(shoppingCartService.getShoppingCartHistory(username));
    }

    @PostMapping
    public ResponseEntity addProductToShoppingCard(@RequestBody @Valid AddProductToShoppingCartRequest request)
            throws ProductNotFoundException {

        shoppingCartService.addProductToShoppingCart(request.getProductId(),
                request.getQuantity(),
                request.getUsername());

        return ResponseEntity.ok(null);
    }

    @PostMapping("/item/increment/{id}")
    public ResponseEntity incrementQuantity(@PathVariable Long id) throws MaxQuantityReachedException {
        shoppingCartItemService.incrementQuantity(id);

        return ResponseEntity.ok(null);
    }

    @PostMapping("/item/decrement/{id}")
    public ResponseEntity decrementQuantity(@PathVariable Long id) throws MinQuantityReachedException {
        shoppingCartItemService.decrementQuantity(id);

        return ResponseEntity.ok(null);
    }

    @PostMapping("/delete/{shoppingCartId}/{itemId}")
    public ResponseEntity decrementQuantity(@PathVariable Long shoppingCartId,
                                            @PathVariable Long itemId) {

        shoppingCartService.deleteItem(shoppingCartId,itemId);

        return ResponseEntity.ok(null);
    }

    @PostMapping("/buy")
    public ResponseEntity buy(@RequestBody @Valid BuyRequest buyRequest) throws InvalidQuantityException, FailedToBuyException, ProductNotFoundException {
        log.info("BuyRequest: {}",buyRequest);
        shoppingCartService.buy(buyRequest);

        return ResponseEntity.ok(null);
    }

}
