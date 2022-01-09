package com.vax.rest.ws;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Service;

import com.vax.rest.inter.CustomerService;
import com.vax.rest.jaxb.Customer;

@Service
@Path("/webshop")
public class CustomerServiceImpl implements CustomerService {
	
private long currentId = 123;
    
    private Map<Long, Customer> customers = new HashMap<Long, Customer>();
    
    /**
     * Pošto je HTTP stateless, init() metoda se izvršava prilikom 
     * svakog zahteva.
     * 
     * U realnim slučajevima resurse skladištiti u XMLDB, RDB i sl.
     * 
     */
    public CustomerServiceImpl() {
        init();
    }

    @GET
    @Path("/customers/{id}/")
    @Produces("application/xml")
    public Customer getCustomer(@PathParam("id") String id) {
        System.out.println("Invoking getCustomer, with a Customer id: " + id);
        Long idNumber = Long.valueOf(id);
        Customer c = customers.get(idNumber);
        return c;
    }

    @PUT
    @Path("/customers/")
    public Response updateCustomer(Customer customer) {
        System.out.println("Invoking updateCustomer, with a Customer id: " + customer.getId());
        Customer c = customers.get(customer.getId());
        Response r;
        if (c != null) {
            customers.put(customer.getId(), customer);
            r = Response.ok().type("application/xml").entity(customer).build();
        } else {
            r = Response.notModified().build();
        }

        return r;
    }

    @POST
    @Path("/customers/")
    public Response addCustomer(Customer customer, @Context UriInfo uriInfo) {
        System.out.println("Invoking addCustomer, with a Customer whose name is: " + customer.getName());
        customer.setId(++currentId);

        customers.put(customer.getId(), customer);
        
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(currentId));

        return Response.created(builder.build()).type("application/xml").entity(customer).build();
    }

    @DELETE
    @Path("/customers/{id}/")
    public Response deleteCustomer(@PathParam("id") String id) {
    	System.out.println("Invoking deleteCustomer, with a Customer id: " + id);
        long idNumber = Long.parseLong(id);
        Customer c = customers.get(idNumber);

        Response r;
        if (c != null) {
            r = Response.ok().build();
            customers.remove(idNumber);
        } else {
            r = Response.notModified().build();
        }

        return r;
    }

    /**
	 * Vrši inicijalizaciju Customer i Order objekata kao i odgovarajućih
	 * lokalnih struktura podataka.
	 * 
	 */
    final void init() {
        Customer c = new Customer();
        c.setName("Pera Perić");
        c.setId(123);
        customers.put(c.getId(), c);

    }


}
