public class FixedDepositPolicy implements DepositPolicy {
    private final Money deposit;

    public FixedDepositPolicy(Money deposit) {
        this.deposit = deposit;
    }

    @Override
    public Money depositFor(BookingRequest req) {
        return deposit;
    }
}
