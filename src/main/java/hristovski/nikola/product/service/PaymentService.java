package hristovski.nikola.product.service;

public interface PaymentService {

    boolean payment(Double amount, String cardholderName, String cardNumber, String validUntil, String ccv);
}
