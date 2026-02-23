import java.util.List;

public interface AddOnPricing {
    Money totalFor(List<AddOn> addOns);
}
