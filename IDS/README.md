Tasks:

For each of the following tasks the client and the server side of the system have to be modified.
Furthermore, there is a shared part where modifications might be necessary. Import the provided
assignment template which consist of a multi-project Gradle build. There is a root project containing sub-projects for the client, the server, and a shared one. You can import the root project
idstrsys into your IDE as existing gradle project. In Eclipse, all the sub-projects are imported as
own projects. An overview of these projects and what to do where is given in the following:
• client: This project contains the Swing-GUI of the existing system. Additional “backend
implementations” (at least one for UDP and one for AMQP) must implement the interface
TicketManagementBackend and be registered. Check the JavaDoc documentation of the
class Main for more details.
• server: This project contains a very basic server backend implementation, which currently
offers no remote access possibilities. Implement the Interface RemoteAccess and register
your implementation as described in class Main of the server project.
• shared: Contains all classes which are used on the server and the client side (i.e., the Ticket
representations). You can also add additional classes, e.g., for UDP message formats if you
like.
• idistrsys: This project is the root project. For this assignment you might do configuration
changes in it, but in general there are no changes necessary here.

Part 1: UDP communication
1. Basic level: Create the possibility to send a newly created ticket to the server by implementing the method createNewTicket(...) of the interface TicketManagementBackend
and by providing a basic implementation of the RemoteAccess interface on the server side.
A one way communication is sufficient here - so the createNewTicket(...) can return null,
or an empty Ticket.
2. Advanced Level: Of course, the basic solution is not really feature complete, so: Create
a basic communication protocol in order to fully implement the TicketManagementBackend
by adding the ability to update Tickets and to retrieve a list of existing Tickets from the
server. Ensure that the server can handle multiple simultaneous requests from different
clients concurrently.

Part 2: AMQP communication
1. Basic Level: Implement a basic one-way communication protocol between the clients and
the backend system. Ensure that messages are delivered to at least one Queue. Messages
from the clients to the backend should be stored persistently in a Queue during forwarding.
2. Advanced level: Improve the protocol to be able to receive replies from the backend server.
As the number of clients is unknown, it is not possible to create the queues for the replies
upfront. Therefore “temporary queues” have to be created during the runtime by the clients.

Query the API:

So far, the TicketManager 5000 can fulfill basic requirements like creating, updating, and retrieving tickets. In the second part, the system must be extended with search functionalities. The
possibility to query the list of tickets is already available on the client-side GUI, however, with
no functionality. Searching must be performed by requesting the remote RESTful API. It is not
allowed to query an existing database on the side of the client. Integrate the searching capabilities
at reasonable places in the RESTful API.
1. Basic level: Implement the functionality defined inside the TicketSearchBackend to allow
for searching for a ticket by name and by a combination of name and type. Search by name
should be implemented in the way that it returns all the Tickets which contain the name
either in the reporter, topic or description attribute. When the type is passed in as
well, only Tickets with this specific type can be found. If there is no such type, return an
appropriate response. Please note: All results must be sorted by priority.
