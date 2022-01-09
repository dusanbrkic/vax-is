package com.vax.rest.inter;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.vax.rest.jaxb.Customer;

public interface CustomerService {
	
	@GET
	@Path("/customers/{id}/")
	@Produces("application/xml")
	public Customer getCustomer(@PathParam("id") String id);

	@PUT
	@Path("/customers/")
	public Response updateCustomer(Customer customer);

	@POST
	@Path("/customers/")
	public Response addCustomer(Customer customer, @Context UriInfo uriInfo);

	@DELETE
	@Path("/customers/{id}/")
	public Response deleteCustomer(@PathParam("id") String id);

}
