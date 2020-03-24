package hristovski.nikola.product.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public boolean payment(Double amount, String cardholderName, String cardNumber, String validUntil, String ccv) {
        return true;
    }
}
