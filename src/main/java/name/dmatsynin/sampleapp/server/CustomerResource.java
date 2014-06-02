package name.dmatsynin.sampleapp.server;



import name.dmatsynin.sampleapp.entity.Customer;
import name.dmatsynin.sampleapp.service.CustomerProvider;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.inject.Inject;
import javax.ws.rs.Produces;
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
        return Response.noContent().build();
    }


}
