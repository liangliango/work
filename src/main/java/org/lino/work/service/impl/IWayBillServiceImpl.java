package org.lino.work.service.impl;

import org.lino.work.base.bean.CustomerClear;
import org.lino.work.base.bean.Income;
import org.lino.work.base.bean.WayBill;
import org.lino.work.iobus.dao.ICustomerClearDao;
import org.lino.work.iobus.dao.ICustomerDao;
import org.lino.work.iobus.dao.IIncomeDao;
import org.lino.work.iobus.dao.IWayBillDao;
import org.lino.work.service.IWayBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service(value = "IwayBillService")
@Transactional(propagation = Propagation.REQUIRED)
public class IWayBillServiceImpl implements IWayBillService {

    @Autowired
    private IWayBillDao wayBillDao;

    @Autowired

    private IIncomeDao incomeDao;

    @Autowired
    private ICustomerClearDao customerClearDao;

    @Override
    public WayBill findWayBillByBillId(String billId) {
        return wayBillDao.findWayBillByBillId(billId);
    }

    static final String HYD = "HYD";

    @Override
    public boolean updateWayBillByBillId(String billId, WayBill wayBill) {

        try {
            WayBill temp = wayBillDao.findWayBillByBillId(billId);
            wayBill.setBillId(temp.getBillId());
            WayBill save = wayBillDao.save(wayBill);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deleteWayBillByBillId(String billId) {

        try {
            wayBillDao.deleteByBillId(billId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public org.springframework.data.domain.Page<WayBill> findWayBillbyState(String state, Pageable pageable) {
        return wayBillDao.findByState(state,pageable);
    }

    @Override
    public Page<WayBill> findAllWayBill(Pageable pageable) {
        return wayBillDao.findAll(pageable);
    }

    @Transactional
    @Override
    public boolean addWayBIll(WayBill waybill) {

        try {
            String id = HYD+System.currentTimeMillis();
            waybill.setBillId(id);
            wayBillDao.save(waybill);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<WayBill> findWayBillByState1() {

        return wayBillDao.findWayBillByState("未到");
    }

    @Transactional
    @Override
    public boolean updateWayBillByBillId1(String billId) {

        try {
            LocalDate localDate = LocalDate.now();
            String month = localDate.getYear()+"-"+localDate.getMonth().getValue();
            WayBill wayBill = wayBillDao.findByBillId(billId);
            wayBill.setState("已结算");
            wayBill.setClear(true);
            Income income = new Income();
            CustomerClear customerClear = new CustomerClear();
            customerClear.setWayBillId(wayBill.getBillId());
            customerClear.setIsClear(true);
            customerClear.setInsurance(wayBill.getInsurance());
            customerClear.setFreight(wayBill.getFreight());
            customerClear.setId("KH"+System.currentTimeMillis());
            customerClear.setClearDate(new Date(System.currentTimeMillis()));
            customerClear.setCustomerId(wayBill.getPayCustomer());
            customerClear.setMoney(wayBill.getFreight()+wayBill.getInsurance());

            income.setProfit(wayBill.getFreight()+wayBill.getInsurance());
            income.setPayout(0);
            income.setOther(0);
            income.setInsuranceFee(wayBill.getInsurance());
            income.setIncome(wayBill.getFreight()+wayBill.getInsurance());
            income.setCarriageFee(wayBill.getFreight());
            income.setWage(0);
            income.setMonth(month);

            wayBillDao.save(wayBill);
            incomeDao.save(income);
            customerClearDao.save(customerClear);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Page<WayBill> findWayBillbyStateAndSendId(String state, String customerId, Pageable pageable) {

        return wayBillDao.findAllByStateAndSendId(state,customerId,pageable);
    }

    @Override
    public Page<WayBill> findAllWayBillBySendId(String sendId, Pageable pageable) {
        return wayBillDao.findAllBySendId(sendId,pageable);
    }

    @Override
    public Page<WayBill> findWayBillbyStateAndReciverId(String state, String reciverId, Pageable pageable) {
        return wayBillDao.findByStateAndReciverId(state,reciverId,pageable);
    }

    @Override
    public Page<WayBill> findAllWayBillByReciverId(String reciverId, Pageable pageable) {
        return wayBillDao.findAllByReciverId(reciverId,pageable);
    }

    @Override
    public List<WayBill> findAllCanClearByPayCustomer(String payCustomer) {
        return wayBillDao.findByPayCustomerAndState(payCustomer,"已到");
    }

    @Transactional
    @Override
    public String wayBillClearByBillId(String billId) {
        try {
            this.updateWayBillByBillId1(billId);
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

}
