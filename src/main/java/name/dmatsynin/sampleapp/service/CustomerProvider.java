package name.dmatsynin.sampleapp.service;

import name.dmatsynin.sampleapp.entity.Customer;

import java.util.List;

/**
 * Created by: dmatsynin
 * Date: 6/2/14
 */
public interface CustomerProvider {

    Customer getByName(String name);

    List getAll();

    Customer insert(Customer customer);

    Customer update(Customer customer);

    Customer getById(Long id);

    void delete(Customer customer);

}
