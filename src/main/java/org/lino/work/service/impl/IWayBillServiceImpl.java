package org.lino.work.service.impl;

import org.lino.work.base.bean.WayBill;
import org.lino.work.iobus.dao.IWayBillDao;
import org.lino.work.service.IWayBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "IwayBillService")
@Transactional(propagation = Propagation.REQUIRED)
public class IWayBillServiceImpl implements IWayBillService {

    @Autowired
    private IWayBillDao wayBillDao;

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
    public WayBill findWayBillbyState(String state) {
        return wayBillDao.findByState(state);
    }

    @Override
    public List<WayBill> findAllWayBill() {
        return wayBillDao.findAll();
    }

    @Override
    public boolean addWayBIll(WayBill waybill) {

        try {
            String id = HYD+Math.random()*1000000;
            while(wayBillDao.findByBillId(id).getBillId()!=null){
                id = HYD+Math.random()*1000000;
            }
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

        return wayBillDao.findWayBillByState("待发");
    }

}
