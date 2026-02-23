public class HostelBookingService {
    private final HostelFeeCalculator calculator;
    private final FakeBookingRepo repo;
    private final BookingIdGenerator idGenerator;

    public HostelBookingService(HostelFeeCalculator calculator, FakeBookingRepo repo, BookingIdGenerator idGenerator) {
        this.calculator = calculator;
        this.repo = repo;
        this.idGenerator = idGenerator;
    }

    public void process(BookingRequest req) {
        FeeBreakdown breakdown = calculator.calculate(req);
        ReceiptPrinter.print(req, breakdown.monthly, breakdown.deposit);
        String bookingId = idGenerator.nextId();
        repo.save(bookingId, req, breakdown.monthly, breakdown.deposit);
    }
}
