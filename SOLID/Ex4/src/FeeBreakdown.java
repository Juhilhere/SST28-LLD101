public class FeeBreakdown {
    public final Money monthly;
    public final Money deposit;

    public FeeBreakdown(Money monthly, Money deposit) {
        this.monthly = monthly;
        this.deposit = deposit;
    }

    public Money totalDueNow() {
        return monthly.plus(deposit);
    }
}
