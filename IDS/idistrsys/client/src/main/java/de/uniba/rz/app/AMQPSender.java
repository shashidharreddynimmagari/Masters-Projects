package de.uniba.rz.app;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import de.uniba.rz.entities.Ticket;

public class AMQPSender {

	private final String hostname;
	private final String routingKey;

	private final ConnectionFactory connFactory = new ConnectionFactory();

	public AMQPSender(String hostname, String routingKey) {
		this.hostname = hostname;
		this.routingKey = routingKey;

	}

	public void sendMessage(Object message) {

		// Note: Could be omitted because localhost is set be default
		connFactory.setHost(hostname);

		try (Connection connection = connFactory.newConnection();) {
			Channel channel = connection.createChannel();

			/*
			 * Note that the declared queue is automatically bound to the pre-declared
			 * (default) exchange of RabbitMQ. The address of the exchange is an empty
			 * string ("") and the type is always "direct". The name of the queue will be
			 * the binding key that is evaluated against the routing key determining an
			 * incoming message's destination. That implies that it is impossible to send a
			 * message directly to the queue from the client's side. The message always has
			 * to pass an exchange.
			 */
			channel.queueDeclare(routingKey, false, false, false, null);

			/*
			 * Alternative - Passive Declaration: Just check if the queue has already been
			 * created. This is non-operational (no-op) and may cause an IOException if
			 * there is no queue. Passive declaration does no redeclaration
			 */
			// channel.queueDeclarePassive(routingKey);

			// It might be a good practice to define a custom exchange with an explicit
			// binding of the previously declared queue.
			// TODO: What are the necessary steps to that and which type of exchange should
			// be used?

			// Send message to the default exchange ("") with the queueName as routing key

			// String queueName = channel.queueDeclare().getQueue();
			if (message instanceof Ticket) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(message);
				oos.flush();
				channel.basicPublish("", routingKey, null, baos.toByteArray());
			} else if (message instanceof String) {
				channel.basicPublish("", routingKey, null, ((String) message).getBytes());
			}

		} catch (IOException e) {
			// TODO: Think of an appropriate exception handling strategy (e.g., retrying,
			// logging,...)
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO: Think of an appropriate exception handling strategy (e.g., retrying,
			// logging,...)
			e.printStackTrace();
		}
	}
}
