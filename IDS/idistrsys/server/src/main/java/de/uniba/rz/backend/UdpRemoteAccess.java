package de.uniba.rz.backend;

import java.io.IOException;
import java.net.*;

public class UdpRemoteAccess implements RemoteAccess {
    private int port;
    private boolean active = true;

    public UdpRemoteAccess(String port) {
        this.port = Integer.valueOf(port);
    }

    @Override
    public void prepareStartup(TicketStore ticketStore) {
        active = true;
        System.out.println("\t [SERVER]: Start listening on port " + port
                + " for messages.");

        try (DatagramSocket socket = new DatagramSocket(null)) {
            SocketAddress address = new InetSocketAddress(port);
            socket.bind(address);

            while(active) {
                byte[] data = new byte[2048];
                DatagramPacket packet = new DatagramPacket(data, data.length);
                try {
                    socket.receive(packet);

                    //Create thread to handle packet
                    new PacketHandler(packet, ticketStore).start();
                } catch (SocketTimeoutException e) {
                    System.out.println("[ERROR]: Timeout");
                }
            }
        } catch(SocketException e) {
            System.out.println("[ERROR]: SocketException");
        }
        catch (IOException e) {
            System.out.println("[ERROR]: IOException");
        }
    }

    @Override
    public void shutdown() {
        active = false;
        System.out.println("Server stopped.");
    }

    @Override
    public void run() {
        prepareStartup(new SimpleTicketStore());
    }
}
