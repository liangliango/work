package org.lino.work.iobus.dao;

import org.lino.work.base.bean.CustomerClear;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerClearDao extends JpaRepository<CustomerClear, Long> {


    public CustomerClear findByWayBillId(String billCode);

    public Page<CustomerClear> findAllByCustomerId(String customerId, Pageable pageable);

    public Page<CustomerClear> findAllByCustomerIdAAndAndIsClear(String customerId, boolean isClear, Pageable pageable);

}