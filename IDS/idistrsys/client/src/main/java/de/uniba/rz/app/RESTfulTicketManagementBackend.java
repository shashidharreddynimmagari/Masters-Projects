package de.uniba.rz.app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import de.uniba.rz.entities.Priority;
import de.uniba.rz.entities.Ticket;
import de.uniba.rz.entities.TicketException;
import de.uniba.rz.entities.Type;



public class RESTfulTicketManagementBackend implements TicketManagementBackend {
	
	AtomicInteger nextId;
	String hostname;
	int port;

    public RESTfulTicketManagementBackend(String host, int port)
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

		Client client = ClientBuilder.newClient();
		
		Ticket newTicket = new Ticket(nextId.getAndIncrement(), reporter, topic, description, type, priority);
		Entity<Ticket> entity = Entity.json(newTicket);
		
		Response response = client.target("http://localhost:"+port+"")
				.path("/ticket")
				.request()
				.header("Content-Type", "application/json")
				.post(entity);
		
		System.out.println("Status: " + response.getStatus() + "\n Location: " + response.getLocation());
		
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