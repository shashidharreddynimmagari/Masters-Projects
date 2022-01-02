package de.uniba.rz.backend.service;

import de.uniba.rz.backend.SimpleTicketStore;
import de.uniba.rz.backend.TicketStore;
import de.uniba.rz.entities.Ticket;

import javax.ws.rs.core.Response;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TicketService {
    private static TicketStore ticketStore;

    public static void setTicketStore(TicketStore store) {
        ticketStore = store;
    }

    public static Ticket createNewTicket(Ticket ticket) {
        return ticketStore.storeNewTicket(ticket.getReporter(), ticket.getTopic(),ticket.getDescription(), ticket.getType(), ticket.getPriority());
    }

    public static List<Ticket> searchByName(String name) {
        List<Ticket> all = ticketStore.getAllTickets();
        List<Ticket> output = all.stream().filter(t -> t.getReporter().contains(name) || t.getDescription().contains(name) || t.getTopic().contains(name))
                .sorted(Comparator.comparing(Ticket::getPriority)).collect(Collectors.toList());
        return output;
    }
}
