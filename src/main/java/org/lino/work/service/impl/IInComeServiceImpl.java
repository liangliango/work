package org.lino.work.service.impl;

import org.lino.work.base.bean.Income;
import org.lino.work.iobus.dao.IIncomeDao;
import org.lino.work.service.IInComeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("IInComeService")
public class IInComeServiceImpl implements IInComeService {

    @Autowired
    IIncomeDao incomeDao;

    @Override
    public Income countIncomeByMonth() {

        String month = ""+new Date().getMonth()+new Date().getYear();
        Income income = new Income();
        income.setCarriageFee(incomeDao.countcarriageFee(month));
        income.setIncome(incomeDao.counincome(month));
        income.setInsuranceFee(incomeDao.countinsuranceFee(month));
        income.setOther(incomeDao.countother(month));
        income.setPayout(incomeDao.countpayout(month));
        income.setProfit(incomeDao.counprofit(month));
        income.setTruckFee(incomeDao.counttruckFee(month));
        return income;
    }
}
