package de.uniba.rz.backend.post;

import de.uniba.rz.backend.service.TicketService;
import de.uniba.rz.entities.Ticket;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("ticket")
@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class Create {

    @POST
    public Response createNewTicket(Ticket ticket, @Context UriInfo uriInfo) {
        ticket = TicketService.createNewTicket(ticket);

        if (ticket == null) {
            throw new WebApplicationException("Invalid request body" , 400);
        }

        UriBuilder path = uriInfo.getAbsolutePathBuilder();
        path.path(Integer.toString(ticket.getId()));
        return Response.created(path.build()).build();
    }

}
