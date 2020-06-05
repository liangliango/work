package org.lino.work.iobus.dao;

import org.lino.work.base.bean.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ICustomerDao extends JpaRepository<Customer, String> {

    public Customer findByCustomerId(String customerId);

    @Query(value = "select customerId from customer")
    public List<String> findAllCustomerId();

    public void deleteByCustomerId(String customerId);

}
