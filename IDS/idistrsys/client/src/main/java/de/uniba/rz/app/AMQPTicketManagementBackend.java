package de.uniba.rz.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.uniba.rz.entities.Priority;
import de.uniba.rz.entities.Ticket;
import de.uniba.rz.entities.TicketException;
import de.uniba.rz.entities.Type;

public class AMQPTicketManagementBackend implements TicketManagementBackend{

	private String hostname, routingkey;
	HashMap<Integer, Ticket> jmsTicketStore = new HashMap<>();
	AMQPSender senderQ;
	
	public AMQPTicketManagementBackend(String arghostname, String argroutingkey) {
		this.hostname = arghostname;
		this.routingkey = argroutingkey;
	}
	
	@Override
	public void triggerShutdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ticket createNewTicket(String reporter, String topic, String description, Type type, Priority priority)
			throws TicketException {
		
		Ticket newTicket = new Ticket(0, reporter, topic, description, type, priority);
		senderQ = new AMQPSender(hostname, routingkey);
		
		///byte [] byteMessage = newTicket.getBytes();
		senderQ.sendMessage(newTicket);
		
		//newTicket = (Ticket) senderQ.receiveMsg();
		
		jmsTicketStore.put(newTicket.getId(), newTicket);
		return (Ticket) newTicket.clone();
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
