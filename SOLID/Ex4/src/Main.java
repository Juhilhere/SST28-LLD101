import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");
        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));
        HostelBookingService service = new HostelBookingService(
                new HostelFeeCalculator(),
                new FakeBookingRepo(),
                new FixedSeedBookingIdGenerator());
        service.process(req);
    }
}
