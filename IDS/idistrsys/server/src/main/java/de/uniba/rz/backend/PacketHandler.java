package de.uniba.rz.backend;


import de.uniba.rz.entities.Ticket;

import java.io.*;
import java.net.DatagramPacket;

public class PacketHandler extends Thread{

    private final DatagramPacket packet;
    private TicketStore store;

    public PacketHandler(DatagramPacket packet, TicketStore store)
    {
        this.packet = packet;
        this.store = store;
    }

    public void run() {
        byte[] receivedData = packet.getData();


        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(receivedData);
        try (ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream)) {
            Object req = ois.readObject();
            if (req instanceof Ticket) {
                Ticket ticket = (Ticket) req;

                store.storeNewTicket(ticket.getReporter(), ticket.getTopic(),ticket.getDescription(), ticket.getType(), ticket.getPriority());
                System.out.println("[Ticket stored]: "+ ticket.toString());

            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
