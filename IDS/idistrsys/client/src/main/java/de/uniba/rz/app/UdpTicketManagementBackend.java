package de.uniba.rz.app;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import de.uniba.rz.entities.Priority;
import de.uniba.rz.entities.Ticket;
import de.uniba.rz.entities.TicketException;
import de.uniba.rz.entities.Type;

import java.io.IOException;
import java.net.*;

public class UdpTicketManagementBackend implements TicketManagementBackend {
	
	AtomicInteger nextId;
	String hostname;
	int port;

    public UdpTicketManagementBackend(String host, int port)
    {
        nextId = new AtomicInteger(1);
        hostname = host;
        this.port = port;
    }


	@Override
	public void triggerShutdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ticket createNewTicket(String reporter, String topic, String description, Type type, Priority priority)
			throws TicketException {

		Ticket newTicket = new Ticket(nextId.getAndIncrement(), reporter, topic, description, type, priority);
		try (DatagramSocket clientSocket = new DatagramSocket(null)) {

			SocketAddress serverAddress = new InetSocketAddress(hostname, port);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(newTicket);
			oos.flush();

			// get the byte array of the object
			//byte[] contentData = newTicket.toString().getBytes();
			byte[] contentData = baos.toByteArray();

			DatagramPacket packet = new DatagramPacket(contentData, contentData.length, serverAddress);
			
			clientSocket.send(packet);			
			 
			clientSocket.close();
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newTicket;
	}

	@Override
	public List<Ticket> getAllTickets() throws TicketException {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	@Override
	public Ticket getTicketById(int id) throws TicketException {
		// TODO Auto-generated method stub
		return new Ticket();
	}

	@Override
	public Ticket acceptTicket(int id) throws TicketException {
		// TODO Auto-generated method stub
		return new Ticket();
	}

	@Override
	public Ticket rejectTicket(int id) throws TicketException {
		// TODO Auto-generated method stub
		return new Ticket();
	}

	@Override
	public Ticket closeTicket(int id) throws TicketException {
		// TODO Auto-generated method stub
		return new Ticket();
	}

}
