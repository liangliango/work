package org.lino.work.service.impl;

import org.lino.work.base.bean.*;
import org.lino.work.iobus.dao.ICarriageDao;
import org.lino.work.iobus.dao.ICustomerClearDao;
import org.lino.work.iobus.dao.IDriverClearDao;
import org.lino.work.iobus.dao.IWayBillDao;
import org.lino.work.service.IClearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;

public class ClearServiceImpl implements IClearService {

    @Autowired
    private IDriverClearDao driverClearDao;

    @Autowired
    private ICarriageDao carriageDao;

    @Autowired
    private IWayBillDao wayBillDao;

    @Autowired
    private ICustomerClearDao customerClearDao;

    @Override
    public Page<DriverClear> selectAllDrclear(String driverId, Pageable pageable) {
        return driverClearDao.findAllByDriverId(driverId, pageable);
    }

    @Override
    public Page<DriverClear> findNotClear(String driverId,Pageable pageable) {
        return driverClearDao.findAllByDriverIdAndIsClear(driverId, false, pageable);
    }

    @Override
    public Page<DriverClear> findIsClear(String driverId, Pageable pageable) {
        return driverClearDao.findAllByDriverIdAndIsClear(driverId, true, pageable);
    }

    @Override
    public boolean drClear(String billId) {
        try {
            Carriage carriage = carriageDao.findByBillId(billId);
            carriage.setClear(true);
            carriageDao.save(carriage);
            DriverClear driverClear = driverClearDao.findByCarriageId(billId);
            driverClear.setIsClear(true);
            driverClear.setClearDate(new Date());
            driverClearDao.save(driverClear);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public DriverClear findByBillId(String billId) {
        return driverClearDao.findByCarriageId(billId);
    }

    @Override
    public Page<CustomerClear> selectAllCusclear(String customerId, Pageable pageable) {
        return customerClearDao.findAllByCustomerId(customerId, pageable);
    }

    @Override
    public Page<CustomerClear> findCusNotClear(String customerId, Pageable pageable) {
        return customerClearDao.findAllByCustomerIdAAndAndIsClear(customerId, false, pageable);
    }

    @Override
    public Page<CustomerClear> findCusIsClear(String customerId, Pageable pageable) {
        return customerClearDao.findAllByCustomerIdAAndAndIsClear(customerId, true, pageable);
    }

    @Override
    public boolean cuClear(String wayBillId) {
        try {
            WayBill wayBill = wayBillDao.findById(wayBillId);
            wayBill.setClear(true);
            wayBill.setClearDate(new Date());
            wayBillDao.save(wayBill);
            CustomerClear customerClear = customerClearDao.findByWayBillId(wayBillId);
            customerClear.setIsClear(true);
            customerClear.setClearDate(new Date());
            customerClearDao.save(customerClear);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
            e.printStackTrace();
            return false;
        }
    }

}
