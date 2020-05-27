package org.lino.work.iobus.dao;

import org.lino.work.base.bean.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ICustomerDao extends JpaRepository<Customer, Long> {

    public Customer findByCustomerId(String customerCode);

    @Query(value = "select customerId from customer")
    public List<String> findAllCustomerId();

    public boolean deleteByCustomerId(String customerId);

}
