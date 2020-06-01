package org.lino.work.service.impl;

import org.lino.work.base.bean.Carriage;
import org.lino.work.base.bean.DriverClear;
import org.lino.work.base.bean.Income;
import org.lino.work.base.bean.WayBill;
import org.lino.work.base.util.Result;
import org.lino.work.busi.controller.ReturnType;
import org.lino.work.iobus.dao.ICarriageDao;
import org.lino.work.iobus.dao.IDriverClearDao;
import org.lino.work.iobus.dao.IIncomeDao;
import org.lino.work.iobus.dao.IWayBillDao;
import org.lino.work.service.ICarriageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service("ICarriageService")
public class ICarriageServiceImpl implements ICarriageService {

    static final String YSHT = "YSHT";
    @Autowired
    ICarriageDao carriageDao;

    @Autowired
    IWayBillDao wayBillDao;

    @Autowired
    IDriverClearDao driverClearDao;

    @Autowired
    IIncomeDao incomeDao;

    @Override
    public String addCarriage(Carriage carriage) {

        String id = YSHT+System.currentTimeMillis();
        try {
            carriage.setCarriageId(id);
            carriageDao.save(carriage);
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }

    }

    @Override
    public Carriage findCarriageByCarriageId(String carriageId) {
        return carriageDao.findByCarriageId(carriageId);
    }

    @Override
    public String updateWayBillByBillId(String billId, WayBill wayBill) {
        try {
            wayBill.setBillId(billId);
            wayBillDao.save(wayBill);
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERR";
        }
    }

    @Override
    public String deleteCarriageByCarriageId(String carriageId) {

        try {
            carriageDao.deleteByCarriageId(carriageId);
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERR";
        }
    }

    @Override
    public Page<Carriage> findAllCarriageByState(String state,Pageable pageable) {
        return carriageDao.findAllByState(state,pageable);
    }

    @Override
    public Page<Carriage> findAllCarriage(Pageable pageable) {
        return carriageDao.findAll(pageable);
    }

    @Override
    public String updateCarriage(Carriage carriage) {
        try {
            Carriage temp = carriageDao.findByBillId(carriage.getBillId());
            carriage.setCarriageId(temp.getCarriageId());
            carriageDao.save(carriage);
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    @Override
    public List<Carriage> findAllCarriageByState(String state) {
        return carriageDao.findByState(state);
    }

    @Override
    public Page<Carriage> findCarriageByDriverId(String driverId, Pageable pageable) {
        return carriageDao.findAllByDriverId(driverId,pageable);
    }

    @Override
    public List<Carriage> findAllCarriage2() {
        return carriageDao.findAllByState("未到");
    }

    @Transactional
    @Override
    public String arrive(String carriageId) {

        try {
            Carriage carriage = carriageDao.findByCarriageId(carriageId);
            carriage.setState("已到");
            WayBill wayBill = wayBillDao.findWayBillByBillId(carriage.getBillId());
            wayBill.setState("已到");
            carriageDao.save(carriage);
            wayBillDao.save(wayBill);
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    @Transactional
    @Override
    public String updateWayBillByBillId1(String billId) {
        try {
            LocalDate localDate = LocalDate.now();
            String month = localDate.getYear()+"-"+localDate.getMonth().getValue();
            System.out.println(billId);
            Carriage c = carriageDao.findByCarriageId(billId);
            c.setState("已结算");
            DriverClear driverClear = new DriverClear();
            driverClear.setClearDate(new Date(System.currentTimeMillis()));
            driverClear.setIsClear(true);
            driverClear.setTruckFee(c.getTruckFee());
            driverClear.setInsurance(c.getInsurance());
            driverClear.setFrieght(c.getFrieght());
            driverClear.setDriverId(c.getDriverId());
            driverClear.setCashPledge(c.getCashPledge());
            driverClear.setCarriageId(c.getCarriageId());
            driverClear.setCarFee(c.getCarFee());
            driverClear.setMoney(c.getFrieght()+c.getCashPledge()-c.getCarFee()-c.getTruckFee()-c.getInsurance());
            driverClear.setId("SJ"+System.currentTimeMillis());
            driverClear.setClearDate(new Date(System.currentTimeMillis()));
            Income income = new Income();
            income.setTruckFee(c.getTruckFee());
            income.setProfit(c.getCarFee()+c.getInsurance()+c.getTruckFee()-(c.getCashPledge()+c.getFrieght()));
            income.setPayout(c.getCashPledge()+c.getFrieght());
            income.setOther(0);
            income.setInsuranceFee(c.getInsurance());
            income.setIncome(c.getCarFee()+c.getInsurance()+c.getTruckFee());
            income.setCarriageFee(c.getFrieght());
            income.setWage(0);
            income.setMonth(month);
            carriageDao.save(c);
            driverClearDao.save(driverClear);
            incomeDao.save(income);
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

}
