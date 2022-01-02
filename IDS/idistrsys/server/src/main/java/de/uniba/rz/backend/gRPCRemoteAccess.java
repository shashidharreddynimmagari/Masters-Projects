package de.uniba.rz.backend;

import Utilities.GrpcUtility;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.protobuf.ByteString;

import de.uniba.rz.backend.service.TicketService;


public class gRPCRemoteAccess implements RemoteAccess {
	private Server server;
	static TicketStore store;
	int port = 50051;

	public gRPCRemoteAccess(String port) {
		this.port = Integer.valueOf(port);
	}

	@Override
	public void run() {
		try {
			server = ServerBuilder.forPort(port)
	        .addService(new TicketManagementImpl())
	        .build()
	        .start();
		}
		catch ( IOException e ) {
			System.out.println("Failed to start server");
		}
	}
	
	@Override
	public void prepareStartup(TicketStore ticketStore) {
	    store = ticketStore;
	}

    @Override
    public void shutdown() {
        try {
        	stop();
        }
        catch ( InterruptedException e ) {
        	System.out.println("Error during server shutdown.");
        }
        System.out.println("Server stopped.");
    }

	private void stop() throws InterruptedException {
	  if (server != null) {
	    server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
	  }
	}

    static class TicketManagementImpl extends TicketManagementGrpc.TicketManagementImplBase {
    	@Override
		public void createTicket(TicketRequest req, StreamObserver<TicketResponse> responseObserver) {
			byte[] message = req.getTicket().toByteArray();
			DatagramPacket packet = new DatagramPacket(message, message.length);
                //Create thread to handle packet
            new PacketHandler(packet, store).start();
			
			System.out.println("\t [RECEIVER]: >Received");
			TicketResponse response = TicketResponse.newBuilder().setTicket(req.getTicket()).build();
			responseObserver.onNext(response);
			responseObserver.onCompleted();
		}
    	
    	@Override
    	public void getAllTickets(SearchFilter filter, StreamObserver<SearchResponse> responseObserver) {
			System.out.println("\t [RECEIVER]: >Received");
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos;
			try {
				oos = new ObjectOutputStream(baos);
				oos.writeObject(store.getAllTickets());
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			SearchResponse response = SearchResponse.newBuilder().setTicketList(ByteString.copyFrom(baos.toByteArray())).build();
			responseObserver.onNext(response);
			responseObserver.onCompleted();

    	}

		@Override
		public void getTicketById(GetTicketByIdRequest request, StreamObserver<GetTicketByIdResponse> responseObserver) {
			//super.getTicketById(request, responseObserver);
			System.out.println("Inside get ticket by id");
			//Extract the date
			int ticketId = request.getId();
			de.uniba.rz.entities.Ticket t=null;
			//Get the ticket
			List<de.uniba.rz.entities.Ticket> tickets = store.getAllTickets();
			for(de.uniba.rz.entities.Ticket ticket:tickets) {
				if (ticket.getId() == ticketId) {
					t = ticket;
					break;
				}
			}

			//Create the response
			GetTicketByIdResponse response = GetTicketByIdResponse.newBuilder()
					.setTicketById(GrpcUtility.getRpcTicketFromModelTicket(t))
					.build();

			//Send the response
			responseObserver.onNext(response);

			//complete the RPC call
			responseObserver.onCompleted();

		}
	}

}
