package Utilities;

import de.uniba.rz.entities.Priority;
import de.uniba.rz.entities.Status;
import de.uniba.rz.entities.Ticket;
import de.uniba.rz.entities.Type;

import java.util.ArrayList;
import java.util.List;

public class GrpcUtility {

    private GrpcUtility() {
    }

    public static Ticket getModelTicketFromRpcTicket(de.uniba.rz.backend.Ticket rpcTicket) {
        Ticket ticket = new Ticket();
        ticket.setId(rpcTicket.getId());
        ticket.setDescription(rpcTicket.getDescription());
        ticket.setPriority(Priority.valueOf(rpcTicket.getPriority().name()));
        ticket.setType(Type.valueOf(rpcTicket.getType().name()));
        ticket.setStatus(Status.valueOf(rpcTicket.getStatus().name()));
        ticket.setReporter(rpcTicket.getReporter());
        ticket.setTopic(rpcTicket.getTopic());

        return ticket;
    }


    //    public static List<Ticket> getModelTicketsFromRpcTickets(de.uniba.rz.backend.Ticket> allRpcTickets) {
//        List<Ticket> allModelTickets = new ArrayList<>();
//        for(de.uniba.rz.backend..Ticket ticket:allRpcTickets) {
//            allModelTickets.add(getModelTicketFromRpcTicket(ticket));
//        }
//        return allModelTickets;
//    }
    public static de.uniba.rz.backend.Ticket getRpcTicketFromModelTicket(Ticket modelTicket) {
        de.uniba.rz.backend.Ticket ticket = de.uniba.rz.backend.Ticket.newBuilder()
                .setId(modelTicket.getId())
                .setDescription(modelTicket.getDescription())
                .setPriority(de.uniba.rz.backend.Priority.valueOf(modelTicket.getPriority().name()))
                .setType(de.uniba.rz.backend.Type.valueOf(modelTicket.getType().name()))
                .setStatus(de.uniba.rz.backend.Status.valueOf(modelTicket.getStatus().name()))
                .setReporter(modelTicket.getReporter())
                .setTopic(modelTicket.getTopic())
                .build();
        return ticket;
    }
    public static List<de.uniba.rz.backend.Ticket> getRpcTicketsFromModelTickets(List<Ticket> allModelTickets) {
        List<de.uniba.rz.backend.Ticket> allRpcTickets = new ArrayList<>();
        for(Ticket ticket:allModelTickets) {
            allRpcTickets.add(getRpcTicketFromModelTicket(ticket));
        }
        return allRpcTickets;
    }
}

