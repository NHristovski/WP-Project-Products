package hristovski.nikola.product.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public boolean payment(Double amount, String cardholderName, String cardNumber, String validUntil, String ccv) {
        long i = 0L;
        while ( i < 1_000_000_000L){
            i++;
        }

        return true;
    }
}
