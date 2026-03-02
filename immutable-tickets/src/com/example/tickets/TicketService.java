package com.example.tickets;

import java.util.Arrays;

public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        return new IncidentTicket.Builder(id, reporterEmail, title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .tags(Arrays.asList("NEW"))
                .build();
    }

    public IncidentTicket escalateToCritical(IncidentTicket t) {
        java.util.List<String> updatedTags = new java.util.ArrayList<>(t.getTags());
        updatedTags.add("ESCALATED");

        return t.toBuilder()
                .priority("CRITICAL")
                .tags(updatedTags)
                .build();
    }

    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
        return t.toBuilder()
                .assigneeEmail(assigneeEmail)
                .build();
    }
}
