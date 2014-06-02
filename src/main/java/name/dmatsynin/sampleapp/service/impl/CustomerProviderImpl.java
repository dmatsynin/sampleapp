package name.dmatsynin.sampleapp.service.impl;

import name.dmatsynin.sampleapp.entity.Customer;
import name.dmatsynin.sampleapp.service.CustomerProvider;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


/**
 * Created by: dmatsynin
 * Date: 6/1/14
 */
@Repository
public class CustomerProviderImpl implements CustomerProvider {


    private EntityManager em;

    @PersistenceContext
    protected void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Customer getByName(String name) {
        TypedQuery<Customer> query = em.createQuery(
                "SELECT c FROM Customer c where name = :name", Customer.class);
            query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public List<Customer> getAll() {
        return em.createQuery(
                "SELECT c from Customer c", Customer.class).getResultList();
    }

    @Override
    public Customer insert(Customer customer) {
        em.persist(customer);
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        em.merge(customer);
        em.flush();
        return customer;
    }



}
