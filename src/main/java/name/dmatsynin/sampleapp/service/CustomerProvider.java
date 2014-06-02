package name.dmatsynin.sampleapp.service;

import name.dmatsynin.sampleapp.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * Created by: dmatsynin
 * Date: 6/1/14
 */
public class CustomerProvider {


    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Customer getByNamer() {
       return em.find(Customer.class, null);
    }
}
