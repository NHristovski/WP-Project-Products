package hristovski.nikola.product.model;

import lombok.Data;

@Data
public class CardData {

    private String creditCardNumber;
    private String cardHolderName;
    private String ccv;
    private String validFrom;
    private String validTo;
}
