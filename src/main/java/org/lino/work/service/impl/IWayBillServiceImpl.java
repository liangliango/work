package org.lino.work.service.impl;

import org.lino.work.base.bean.WayBill;
import org.lino.work.iobus.dao.IWayBillDao;
import org.lino.work.service.IWayBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

}
