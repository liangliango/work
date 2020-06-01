package org.lino.work.service.impl;

import org.lino.work.base.bean.EmployeeWage;
import org.lino.work.base.bean.Income;
import org.lino.work.iobus.dao.IEmployeeWageDao;
import org.lino.work.iobus.dao.IIncomeDao;
import org.lino.work.service.IEmployeeWageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("IEmployeeWageService")
public class IEmployeeWageServiceImpl implements IEmployeeWageService {
    @Autowired
    IEmployeeWageDao employeeWageDao;
    @Autowired
    IIncomeDao incomeDao;
    @Transactional
    @Override
    public String addEmpployeeWage(EmployeeWage wage) {

        try {
            EmployeeWage wage1 = employeeWageDao.save(wage);
            Income income = new Income();
            income.setProfit(-(wage1.getBasicWage()+wage1.getExtraWage()+wage1.getPerformanceWage()));
            income.setPayout(wage1.getBasicWage()+wage1.getExtraWage()+wage1.getPerformanceWage());
            income.setOther(0);
            income.setInsuranceFee(0);
            income.setIncome(0);
            income.setCarriageFee(0);
            income.setWage(wage1.getBasicWage()+wage1.getExtraWage()+wage1.getPerformanceWage());
            java.util.Date date = new java.util.Date();
            String s = date.getYear()+date.getMonth()+"";
            income.setMonth(s);
            incomeDao.save(income);
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }


    }
}
