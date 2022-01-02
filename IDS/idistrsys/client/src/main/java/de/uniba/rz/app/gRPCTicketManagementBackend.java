package de.uniba.rz.app;

import Utilities.GrpcUtility;
import de.uniba.rz.backend.*;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.protobuf.ByteString;

import de.uniba.rz.entities.Priority;
import de.uniba.rz.entities.Status;
import de.uniba.rz.entities.Ticket;
import de.uniba.rz.entities.TicketException;
import de.uniba.rz.entities.Type;

public class gRPCTicketManagementBackend implements TicketManagementBackend {
	private final TicketManagementGrpc.TicketManagementBlockingStub blockingStub;
	private ManagedChannel channel;
	private String target = "localhost";
	private String port = "50051";

	public gRPCTicketManagementBackend() {
	    this.channel = ManagedChannelBuilder.forTarget(target + ":" + port)
	    .usePlaintext()
	    .build();
		this.blockingStub = TicketManagementGrpc.newBlockingStub(channel);
	}

	@Override
	public void triggerShutdown() {
	      try {
			channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Ticket createNewTicket(String reporter, String topic, String description, Type type, Priority priority) {
		Ticket newTicket = new Ticket(0, reporter, topic, description, type, priority);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(newTicket);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	    TicketRequest request = TicketRequest.newBuilder().setTicket(ByteString.copyFrom(baos.toByteArray())).build();

	    TicketResponse response;

	    try {
	      response = blockingStub.createTicket(request);
	    } catch (StatusRuntimeException e) {
	    	System.out.println(e.getMessage());
	    }
		return (Ticket) newTicket.clone();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> getAllTickets() throws TicketException {
	    SearchFilter request = SearchFilter.newBuilder().setFilter("").build();

	    SearchResponse response;
	    List<Ticket> ticketList = new ArrayList<>();

	    try {
	      response = blockingStub.getAllTickets(request);
	    } catch (StatusRuntimeException e) {
	    	System.out.println(e.getMessage());
	    	return ticketList;
	    }
	    
        byte[] receivedData = response.getTicketList().toByteArray();
        

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(receivedData);
        try (ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream)) {
            Object req = ois.readObject();
            if (req instanceof List<?>) {
            	ticketList = (List<Ticket>) req;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
	    
		return ticketList;
	}

	@Override
	public Ticket getTicketById(int id) throws TicketException {

		de.uniba.rz.backend.Ticket retrivedTicket;
		System.out.println("Inside get ticket by id");
		GetTicketByIdRequest getTicketRequest = GetTicketByIdRequest.newBuilder()
				.setId(id)
				.build();
		GetTicketByIdResponse getTicketResponse = blockingStub.getTicketById(getTicketRequest);
		retrivedTicket = getTicketResponse.getTicketById();
		return GrpcUtility.getModelTicketFromRpcTicket(retrivedTicket);
	}

	private Ticket getTicketByIdInteral(int id) throws TicketException {
		return new Ticket();
	}

	@Override
	public Ticket acceptTicket(int id) throws TicketException {
		return new Ticket();
	}

	@Override
	public Ticket rejectTicket(int id) throws TicketException {
		return new Ticket();
	}

	@Override
	public Ticket closeTicket(int id) throws TicketException {
		return new Ticket();
	}

}
