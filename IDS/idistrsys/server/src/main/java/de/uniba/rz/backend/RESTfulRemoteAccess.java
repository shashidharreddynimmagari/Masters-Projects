package de.uniba.rz.backend;

import de.uniba.rz.backend.service.TicketService;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.*;

public class RESTfulRemoteAccess implements RemoteAccess {
    private int port;
    private boolean active = true;

    public RESTfulRemoteAccess(String port) {
        this.port = Integer.valueOf(port);
    }

    @Override
    public void prepareStartup(TicketStore ticketStore) {
        TicketService.setTicketStore(ticketStore);
    }

    @Override
    public void shutdown() {
        active = false;
        System.out.println("Server stopped.");
    }

    @Override
    public void run() {
        prepareStartup(new SimpleTicketStore());
        URI baseUri = UriBuilder.fromUri("http://localhost:"+port+"/").build();
        ResourceConfig config = ResourceConfig.forApplicationClass(CrudApi.class);
        JdkHttpServerFactory.createHttpServer(baseUri, config);
        System.out.println("RESTful Server started on Port "+ port);
    }
}
