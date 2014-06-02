package name.dmatsynin.sampleapp;

import name.dmatsynin.sampleapp.entity.Address;
import name.dmatsynin.sampleapp.entity.Customer;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CustomerResourceIT {
    private static String endpointUrl;

    @BeforeClass
    public static void beforeClass() {
        endpointUrl = System.getProperty("service.url");
    }

    @Test
    public void testCuctomerProviderGetAll() throws Exception {
        List<Object> providers = new ArrayList<Object>();
        providers.add(new org.codehaus.jackson.jaxrs.JacksonJsonProvider());
        WebClient client = WebClient.create(endpointUrl + "/customers", providers);
        Response r = client.accept("application/json").get();
        assertEquals(Response.Status.OK.getStatusCode(), r.getStatus());
    }

    @Test
    public void shouldFindById() throws Exception {
        List<Object> providers = new ArrayList<Object>();
        providers.add(new org.codehaus.jackson.jaxrs.JacksonJsonProvider());
        WebClient client = WebClient.create(endpointUrl + "/customers/1", providers);
        Response r = client.accept("application/json").get();
        assertEquals(Response.Status.OK.getStatusCode(), r.getStatus());
        MappingJsonFactory factory = new MappingJsonFactory();
        JsonParser parser = factory.createJsonParser((InputStream) r.getEntity());
        Customer customer = parser.readValueAs(Customer.class);
        assertEquals(1L, customer.getId().longValue());
    }

    @Test
    public void shouldNotFindById() throws Exception {
        List<Object> providers = new ArrayList<Object>();
        providers.add(new org.codehaus.jackson.jaxrs.JacksonJsonProvider());
        WebClient client = WebClient.create(endpointUrl + "/customers/1000", providers);
        Response r = client.accept("application/json").get();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), r.getStatus());
    }


    @Test
    public void  shouldAddNewCustomer() throws Exception {
        List<Object> providers = new ArrayList<Object>();
        providers.add(new org.codehaus.jackson.jaxrs.JacksonJsonProvider());

        Address address = new Address();
        address.setStreetAddress("Unit 2, 27 Remuera road");
        address.setSuburb("Remuera");
        address.setCity("Auckland");
        address.setPostcode("1022");
        Customer customer = new Customer();
        customer.setName("Add New Customer Name");
        customer.setPhoneNumber("22287872");
        customer.setFaxNumber("33887877");
        customer.setAddress(address);

        WebClient client = WebClient.create(endpointUrl + "/customers", providers);
        Response r = client.accept("application/json").type("application/json").post(customer);
        assertEquals(Response.Status.OK.getStatusCode(), r.getStatus());
        MappingJsonFactory factory = new MappingJsonFactory();
        JsonParser parser = factory.createJsonParser((InputStream) r.getEntity());
        Customer addedCustomer = parser.readValueAs(Customer.class);
        assertNotNull(addedCustomer.getId().longValue());
    }

    @Test
    public void  shouldUpdateACustomer() throws Exception {
        List<Object> providers = new ArrayList<Object>();
        providers.add(new org.codehaus.jackson.jaxrs.JacksonJsonProvider());

        Address address = new Address();
        address.setStreetAddress("Unit 3, 27 Remuera road");
        address.setSuburb("Remuera");
        address.setCity("Auckland");
        address.setPostcode("1022");
        Customer customer = new Customer();
        customer.setName("Updated Customer Name");
        customer.setPhoneNumber("22287872");
        customer.setFaxNumber("33887877");
        customer.setAddress(address);

        WebClient client = WebClient.create(endpointUrl + "/customers/2", providers);
        Response r = client.accept("application/json").type("application/json").put(customer);
        assertEquals(Response.Status.OK.getStatusCode(), r.getStatus());
        MappingJsonFactory factory = new MappingJsonFactory();
        JsonParser parser = factory.createJsonParser((InputStream) r.getEntity());
        Customer updatedCustomer = parser.readValueAs(Customer.class);
        assertEquals(customer.getName(), updatedCustomer.getName());
    }


    @Test
    public void shouldDeleteById() throws Exception {
        List<Object> providers = new ArrayList<Object>();
        providers.add(new org.codehaus.jackson.jaxrs.JacksonJsonProvider());

        Address address = new Address();
        address.setStreetAddress("Unit 2, 27 Remuera road");
        address.setSuburb("To be deleted");
        address.setCity("Auckland");
        address.setPostcode("1022");
        Customer customer = new Customer();
        customer.setName("For deletions");
        customer.setPhoneNumber("22287872");
        customer.setFaxNumber("33887877");
        customer.setAddress(address);

        WebClient client = WebClient.create(endpointUrl + "/customers", providers);
        Response r = client.accept("application/json").type("application/json").post(customer);
        assertEquals(Response.Status.OK.getStatusCode(), r.getStatus());
        MappingJsonFactory factory = new MappingJsonFactory();
        JsonParser parser = factory.createJsonParser((InputStream) r.getEntity());
        Customer addedCustomer = parser.readValueAs(Customer.class);
        assertNotNull(addedCustomer.getId().longValue());
        Long id = addedCustomer.getId();

        client = WebClient.create(endpointUrl + "/customers/"+id, providers);
        r = client.accept("application/json").delete();
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), r.getStatus());
    }
}
