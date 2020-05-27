package org.lino.work.service;

import org.lino.work.base.bean.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClearService {

    public Page<DriverClear> selectAllDrclear(String driverId, Pageable pageable);

    public Page<DriverClear> findNotClear(String driverId, Pageable pageable);

    public Page<DriverClear> findIsClear(String driverId, Pageable pageable);

    public boolean drClear(String carriageId);

    public DriverClear findByBillId(String billId);

    public Page<CustomerClear> selectAllCusclear(String customerId, Pageable pageable);

    public Page<CustomerClear> findCusNotClear(String customerId, Pageable pageable);

    public Page<CustomerClear> findCusIsClear(String customerId, Pageable pageable);

    public boolean cuClear(String wayBillId);

}
