package org.lino.work.service;

import org.lino.work.base.bean.Carriage;
import org.lino.work.base.bean.DriverClear;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDriverClearService {
    Page<DriverClear> findDriverClearByDriverIdAndState(String driverId, String state, Pageable pageable);

    DriverClear findDriverClearByCarriageId(String carriageId);

    Page<Carriage> findCarriageByDriverIdAndState(String driverId, String state, Pageable pageable);
}
