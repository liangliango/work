package org.lino.work.service.impl;

import org.lino.work.base.bean.Carriage;
import org.lino.work.base.bean.WayBill;
import org.lino.work.base.util.Result;
import org.lino.work.busi.controller.ReturnType;
import org.lino.work.iobus.dao.ICarriageDao;
import org.lino.work.iobus.dao.IWayBillDao;
import org.lino.work.service.ICarriageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ICarriageService")
public class ICarriageServiceImpl implements ICarriageService {

    static final String YSHT = "YSHT";
    @Autowired
    ICarriageDao carriageDao;

    @Autowired
    IWayBillDao wayBillDao;
    @Override
    public String addCarriage(Carriage carriage) {

        String id = YSHT+Math.random()*1000000+System.currentTimeMillis();
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
    public List<Carriage> findAllCarriageByState(String state) {
        return carriageDao.findAllByState(state);
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

}
