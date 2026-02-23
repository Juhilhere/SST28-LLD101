public class HostelFeeCalculator {
    private final RoomPricing roomPricing;
    private final AddOnPricing addOnPricing;
    private final DepositPolicy depositPolicy;

    public HostelFeeCalculator() {
        this(new DefaultRoomPricing(), new DefaultAddOnPricing(), new FixedDepositPolicy(new Money(5000.00)));
    }

    public HostelFeeCalculator(RoomPricing roomPricing, AddOnPricing addOnPricing, DepositPolicy depositPolicy) {
        this.roomPricing = roomPricing;
        this.addOnPricing = addOnPricing;
        this.depositPolicy = depositPolicy;
    }

    public FeeBreakdown calculate(BookingRequest req) {
        Money baseMonthly = roomPricing.monthlyFor(req.roomType);
        Money addOnMonthly = addOnPricing.totalFor(req.addOns);
        Money monthly = baseMonthly.plus(addOnMonthly);
        Money deposit = depositPolicy.depositFor(req);
        return new FeeBreakdown(monthly, deposit);
    }
}
