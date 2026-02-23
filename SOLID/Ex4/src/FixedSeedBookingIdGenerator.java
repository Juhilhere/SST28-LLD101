public class FixedSeedBookingIdGenerator implements BookingIdGenerator {
    @Override
    public String nextId() {
        return "H-" + (7000 + new java.util.Random(1).nextInt(1000));
    }
}
