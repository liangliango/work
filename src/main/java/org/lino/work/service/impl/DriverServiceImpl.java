package org.lino.work.service.impl;

import org.lino.work.base.bean.*;
import org.lino.work.iobus.dao.*;
import org.lino.work.service.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

public class DriverServiceImpl implements IDriverService {

    private static final String sj="SJ";

    @Autowired
    private IDriverDao driverDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IBillRouteDao billRouteDao;

    @Autowired
    private ICarriageDao carriageDao;

    @Autowired
    private IDriverClearDao driverClearDao;

    @Override
    public Page<Driver> findAllByPage(Pageable pageable) {
        return driverDao.findAll(pageable);
    }

    @Override
    public boolean addNewDriver(Driver driverInfo) {

        String id = sj+(int)Math.random()*100000;
        Driver driverId = driverDao.findByDriverId(id);
        while(driverId != null){
            id = sj+(int)Math.random()*100000;
            driverId = driverDao.findByDriverId(id);
        }
        driverInfo.setDriverId(id);
        try {
            driverDao.save(driverInfo);
            User user = new User();
            user.setLoginId(driverInfo.getDriverId());
            user.setPassword("123456");
            userDao.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateById(Driver driverInfo) {

        try {
            driverDao.save(driverInfo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Driver findById(String id) {
        return driverDao.findByDriverId(id);
    }

    @Override
    public boolean deleteById(String id) {
        return driverDao.deleteByDriverId(id);
    }

    @Override
    public List<String> findAllId() {
        return driverDao.findAllId();
    }

    /**
     * 登记货物路线信息
     *
     * @param billRoute
     * @return
     */
    @Override
    public BillRoute sign(BillRoute billRoute) {
        return billRouteDao.save(billRoute);
    }

    @Override
    public BillRoute findByBillRouteId(String id) {
        return billRouteDao.findByBillId(id);
    }

    @Override
    public Carriage add(Carriage carriage) {
        try {
            DriverClear driverClear = new DriverClear();
            driverClear.setCarFee(carriage.getCarFee());
            driverClear.setCarriageId(carriage.getBillId());
            driverClear.setCashPledge(carriage.getCashPledge());
            driverClear.setDriverId(carriage.getDriverId());
            driverClear.setFrieght(carriage.getFrieght());
            driverClear.setIsClear(false);
            driverClear.setInsurance(carriage.getInsurance());
            driverClear.setTruckFee(carriage.getTruckFee());
            driverClear.setWriteDate(carriage.getWriteDate());
            driverClearDao.save(driverClear);
            return carriageDao.save(carriage);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
            e.printStackTrace();
            return null;
        }
    }
}
