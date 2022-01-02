package de.uniba.rz.backend;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

import javax.annotation.concurrent.ThreadSafe;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import de.uniba.rz.entities.Priority;
import de.uniba.rz.entities.Type;

@ThreadSafe
public class AMQPRemoteAccess implements RemoteAccess {
	private final String hostname;
	private final String queueName;
	
	private final ConnectionFactory connFactory = new ConnectionFactory();
	
	private Thread thread;
	
	private TicketStore store = null;

	public AMQPRemoteAccess(String hostname, String queueName) {
		this.hostname = hostname;
		this.queueName = queueName;
	}

    public AMQPRemoteAccess(String queueName) {
		this.hostname = "localhost";
		this.queueName = queueName;
    }

    @Override
    public void prepareStartup(TicketStore ticketStore) {
    	store = ticketStore;
	}

    @Override
    public void shutdown() {
		System.out.println("\t [RECEIVER]: Stopping to listen for messages.");
		thread.interrupt(); // Ask the PushQueueReceiver for a graceful shutdown
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void run() {
		final BlockingQueue<byte[]> blockingQueue = new ArrayBlockingQueue<byte[]>(1, true);
		
		thread = Thread.currentThread();
				
		// Note: Could be omitted because localhost is set be default
		connFactory.setHost(this.hostname); 

		System.out.println("\t [RECEIVER]: Start waiting for messages");
		
		try (Connection connection = connFactory.newConnection(); Channel channel = connection.createChannel()) {
			channel.queueDeclare(this.queueName, false, false, false, null);
			channel.exchangeDeclare("TicketUpdates", "fanout", true);
			
			// Ensure that we will only get one message at a time from RabbitMQ
			channel.basicQos(1);
			
			/*
			 * This method registers a consumer on the queue by the usage of the consumerTag and a callback object.
			 * We pass the callback object DefaultConsumer, which implements the interface Consumer.
			 * A dispatcher thread within the Java Client Library will call the methods
			 * within this interface, for example, handleDelivery(...). Hence, we have to provide an implementation
			 * to achieve a certain behavior.
			 * 
			 * NOTE: PushQueueReceiver will not call the implemented methods of that interface! 
			 * These methods are called by the thread pool of the dispatcher (one per connection)!
			 */
			// TODO: think of an appropriate strategy to handle the acknowledgement
			channel.basicConsume(this.queueName, true, "Server-1", new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
					try {
						// After receiving, add the message to a thread-safe data structure, like a BlockingQueue
						blockingQueue.put(body);
					} catch (InterruptedException e) {
						// Preserve the interrupt for the caller (channel's thread pool)
						Thread.currentThread().interrupt();
					}
				}
			});
			
			while (!Thread.currentThread().isInterrupted()) {
				try {
					/* The PushQueueReceiver thread will wait until a message becomes available.
					 * Because we will receive messages asynchronously, this may need an indefinite amount of time.
					 * PushQueueReceiver will be suspended (by calling take()) from the CPU while it is waiting for a message (No Pull-API!)
					 */
					byte[] message = blockingQueue.take();
					DatagramPacket packet = new DatagramPacket(message, message.length);
	                    //Create thread to handle packet
                    new PacketHandler(packet, store).start();
					
					System.out.println("\t [RECEIVER]: >Received");
					
					channel.basicPublish("TicketUpdates", "", null, message);
					// TODO: further processing of the message
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
			System.out.println("\t [RECEIVER]: Shutdown complete");
			
		} catch (IOException e) {
			// TODO: Think of an appropriate exception handling strategy (e.g., retrying, logging, ...)
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO: Think of an appropriate exception handling strategy (e.g., retrying, logging, ...)
			e.printStackTrace();
		}
    }
}
