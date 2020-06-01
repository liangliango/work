package org.lino.work.service.impl;

import org.lino.work.base.bean.Income;
import org.lino.work.base.bean.Upd;
import org.lino.work.iobus.dao.IIncomeDao;
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
    @Autowired
    IIncomeDao incomeDao;

    @Transactional
    @Override
    public String upd(Upd upd) {

        try {
            updDao.save(upd);
            Income income = new Income();
            if (upd.getType()=="收入"){
                income.setPayout(0);
                income.setIncome(upd.getMoney());
                income.setOther(upd.getMoney());
            }
            if (upd.getType()=="支出"){
                income.setPayout(upd.getMoney());
                income.setIncome(0);
                income.setOther(-upd.getMoney());
            }

            income.setInsuranceFee(0);
            income.setCarriageFee(0);
            income.setWage(0);
            java.util.Date date = new java.util.Date();
            String s = date.getYear()+date.getMonth()+"";
            income.setMonth(s);
            income.setProfit(income.getIncome()-income.getPayout());
            incomeDao.save(income);
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}
