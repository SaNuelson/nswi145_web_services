package com.novelins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.xml.bind.JAXBElement;

@Path("/contacts")
public class ContactResource {
	@Context
	UriInfo uriInfo;
	
	private static Map<Integer, Contact> contacts = new HashMap<Integer, Contact>();
    
	public ContactResource() {
	    contacts.put(1, new Contact(1, "Jane Doe", "jane.doe@yahoo.com"));
	    contacts.put(2, new Contact(2, "John Doe", "john.doe@yahoo.com"));
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Contact> getContacts() {
	    List<Contact> returnedContacts = new ArrayList<Contact>();
	    returnedContacts.addAll( contacts.values() );
	    return returnedContacts;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response postContacts(JAXBElement<List<Contact>> inCs) {
		List<Contact> cs = inCs.getValue();
		Response res;
		
		int replaced = 0;
		int inserted = 0;
		
		for (Contact c : cs) {
			if (contacts.containsKey(c.getID())) {
				replaced++;
				contacts.remove(c.getID());
				contacts.put(c.getID(), c);
			}
			else {
				inserted++;
				contacts.put(c.getID(), c);
			}
		}
		
		return Response
				.created(uriInfo.getAbsolutePath())
				.entity("Created " + inserted + " entries, replaced " + replaced + " entries.")
				.build();
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{contact}")
	public Contact getContact(@PathParam("contact") String contactId) {
		return contacts.get(Integer.parseInt(contactId));
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{contact}")
    public Response putContact(@PathParam("contact") String contactID, JAXBElement<Contact> contact) {
		
		Contact c = contact.getValue();
		Response res;
		
		if (c.getID() != Integer.parseInt(contactID)) {
			res = Response
					.status(Response.Status.CONFLICT)
					.entity("Number don't match.")
					.build();
		}
		else if (contacts.containsKey(c.getID())) {
			contacts.remove(c.getID());
			contacts.put(c.getID(), c);
			res = Response
					.noContent()
					.build();
		}
		else {
			contacts.put(c.getID(), c);
			res = Response
					.created(uriInfo.getAbsolutePath())
					.build();
		}
		return res;
	}
}
