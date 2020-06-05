package org.lino.work.service.impl;

import cn.hutool.crypto.SecureUtil;
import org.lino.work.base.bean.*;
import org.lino.work.iobus.dao.*;
import org.lino.work.service.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service()
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

    @Autowired
    IUserWithGroupDao userWithGroupDao;
    @Override
    public Page<Driver> findAllByPage(Pageable pageable) {
        return driverDao.findAll(pageable);
    }

    @Transactional
    @Override
    public boolean addNewDriver(Driver driverInfo) {

        String id = sj+System.currentTimeMillis();
        driverInfo.setDriverId(id);
        try {
            driverDao.save(driverInfo);
            User user = new User();
            user.setLoginId(driverInfo.getDriverId());
            String pwd = SecureUtil.md5("123456");
            user.setPassword(pwd);
            userDao.save(user);
            UserWithGroup userWithGroup = new UserWithGroup();
            userWithGroup.setGroupId(3);
            userWithGroup.setUserId(id);
            userWithGroupDao.save(userWithGroup);
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

    @Override
    public Page<Driver> findAllDriver(Pageable pageable) {
        return driverDao.findAll(pageable);
    }

    @Override
    public Driver findDriverByDriverId(String driverId) {
        return driverDao.findByDriverId(driverId);
    }

    @Transactional
    @Override
    public boolean addDriver(Driver driver) {

        this.addNewDriver(driver);
        return false;
    }

    @Transactional
    @Override
    public boolean deleteDriverByDriverId(String driverId) {

        try {
            driverDao.deleteByDriverId(driverId);
            userWithGroupDao.deleteByUserId(driverId);
            userDao.deleteByLoginId(driverId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    @Override
    public String updateDriverByDriverId(String driverId, Driver driver) {

        try {
            driver.setDriverId(driverId);
            driverDao.save(driver);

            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    @Override
    public List<Driver> findAllDriver1() {
        return driverDao.findAll();
    }

}
