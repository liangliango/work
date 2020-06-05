package org.lino.work.iobus.dao;

import org.lino.work.base.bean.CustomerClear;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerClearDao extends JpaRepository<CustomerClear, String> {


}