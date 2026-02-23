public class DefaultRoomPricing implements RoomPricing {
    @Override
    public Money monthlyFor(int roomType) {
        double base;
        switch (roomType) {
            case LegacyRoomTypes.SINGLE -> base = 14000.0;
            case LegacyRoomTypes.DOUBLE -> base = 15000.0;
            case LegacyRoomTypes.TRIPLE -> base = 12000.0;
            default -> base = 16000.0;
        }
        return new Money(base);
    }
}
