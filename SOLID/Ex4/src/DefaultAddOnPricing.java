import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class DefaultAddOnPricing implements AddOnPricing {
    private final Map<AddOn, Double> prices = new EnumMap<>(AddOn.class);

    public DefaultAddOnPricing() {
        prices.put(AddOn.MESS, 1000.0);
        prices.put(AddOn.LAUNDRY, 500.0);
        prices.put(AddOn.GYM, 300.0);
    }

    @Override
    public Money totalFor(List<AddOn> addOns) {
        double total = 0.0;
        for (AddOn addOn : addOns) {
            total += prices.getOrDefault(addOn, 0.0);
        }
        return new Money(total);
    }
}
