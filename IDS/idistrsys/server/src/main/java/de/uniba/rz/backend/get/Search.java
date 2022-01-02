package de.uniba.rz.backend.get;

import de.uniba.rz.backend.service.TicketService;
import de.uniba.rz.entities.Ticket;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("search")
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class Search {

    @GET
    public List<Ticket> searchByName(@QueryParam("name") String name) {
        return TicketService.searchByName(name);
    }

}
