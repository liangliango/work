package org.lino.work.service;

import org.lino.work.base.bean.Carriage;
import org.lino.work.base.bean.DriverClear;
import org.lino.work.iobus.dao.ICarriageDao;
import org.lino.work.iobus.dao.IDriverClearDao;
import org.lino.work.iobus.dao.IDriverDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IDriverClearServiceImpl implements IDriverClearService {
    @Autowired
    IDriverDao driverDao;
    @Autowired
    IDriverClearDao driverClearDao;
    @Autowired
    ICarriageDao carriageDao;
    @Override
    public Page<DriverClear> findDriverClearByDriverIdAndState(String driverId, String state, Pageable pageable) {
        boolean isClear;
        if (state == "未结算"){
            isClear = false;
        }else {
            isClear = true;
        }

        return driverClearDao.findAllByDriverIdAndIsClear(driverId,isClear,pageable);
    }

    @Override
    public DriverClear findDriverClearByCarriageId(String carriageId) {
        return driverClearDao.findByCarriageId(carriageId);
    }

    @Override
    public Page<Carriage> findCarriageByDriverIdAndState(String driverId, String state, Pageable pageable) {
        return carriageDao.findAllByDriverIdAndState(driverId,state,pageable);
    }
}
