public interface DepositPolicy {
    Money depositFor(BookingRequest req);
}
