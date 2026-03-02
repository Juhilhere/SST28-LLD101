import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);

        IncidentTicket assigned = service.assign(t, "agent@example.com");
        IncidentTicket escalated = service.escalateToCritical(assigned);
        System.out.println("\nOriginal ticket (unchanged): " + t);
        System.out.println("Escalated copy: " + escalated);

        List<String> tags = escalated.getTags();
        try {
            tags.add("HACKED_FROM_OUTSIDE");
            System.out.println("\nAfter external tag mutation: " + escalated);
        } catch (UnsupportedOperationException e) {
            System.out.println("\nTags list is immutable, mutation blocked!");
            System.out.println("After external tag mutation attempt: " + escalated);
        }
    }
}
