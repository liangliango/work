package org.lino.work.service.impl;

import org.lino.work.base.bean.BillRoute;
import org.lino.work.iobus.dao.IBillRouteDao;
import org.lino.work.service.IBillRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("IBillRouteService")
public class IBillRouteServiceImpl implements IBillRouteService {


    @Autowired
    IBillRouteDao billRouteDao;

    @Override
    public String addBillRoute(BillRoute billRoute) {


        try {
            billRouteDao.save(billRoute);
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }


    }
}
