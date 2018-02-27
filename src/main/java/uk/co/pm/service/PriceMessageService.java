package uk.co.pm.service;

import uk.co.pm.model.HelloReference;
import uk.co.pm.model.Price;
import uk.co.pm.model.PriceReference;

public class PriceMessageService {
    public static PriceReference getPriceMessage(final Price price) {
        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }
        if(price.getEPIC() == null || "".equals(price.getEPIC())){
            throw new IllegalArgumentException("Person must have an EPIC");
        }
        return new PriceReference("Epic is: " + price.getEPIC());
    }
}
