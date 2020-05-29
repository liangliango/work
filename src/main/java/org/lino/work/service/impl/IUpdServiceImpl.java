package org.lino.work.service.impl;

import org.lino.work.base.bean.Upd;
import org.lino.work.iobus.dao.IUpdDao;
import org.lino.work.service.IUpdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class IUpdServiceImpl implements IUpdService {
    @Autowired
    IUpdDao updDao;

    @Transactional
    @Override
    public String upd(Upd upd) {

        try {
            updDao.save(upd);
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}
