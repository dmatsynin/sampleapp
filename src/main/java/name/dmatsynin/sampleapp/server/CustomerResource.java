package name.dmatsynin.sampleapp.server;



import name.dmatsynin.sampleapp.entity.Customer;
import name.dmatsynin.sampleapp.service.CustomerProvider;

import javax.ws.rs.*;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import java.util.List;

/**
 * Created by: dmatsynin
 * Date: 6/2/14
 */

@Path("/customers")
public class CustomerResource {

    @Inject
    CustomerProvider provider;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers() {
        List<Customer> customers = provider.getAll();
        if (customers.size() > 0) {
            return Response.ok().entity(customers).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("id") Long id) {
        Customer customer = provider.getById(id);
        if (customer == null) {
           return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(customer).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Customer customer) {
        provider.insert(customer);
        return Response.ok().entity(customer).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Customer updatedCustomer) {
        Customer currentCustomer = provider.getById(id);
        if (currentCustomer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        currentCustomer.setAddress(updatedCustomer.getAddress());
        currentCustomer.setFaxNumber(updatedCustomer.getFaxNumber());
        currentCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
        currentCustomer.setName(updatedCustomer.getName());
        provider.update(currentCustomer);
        return Response.ok().entity(currentCustomer).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {

        Customer customer = provider.getById(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        provider.delete(customer);
        return Response.noContent().build();
    }

}
