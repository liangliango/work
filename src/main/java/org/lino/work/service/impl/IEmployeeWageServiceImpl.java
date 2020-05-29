package org.lino.work.service.impl;

import org.lino.work.base.bean.EmployeeWage;
import org.lino.work.iobus.dao.IEmployeeWageDao;
import org.lino.work.service.IEmployeeWageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("IEmployeeWageService")
public class IEmployeeWageServiceImpl implements IEmployeeWageService {
    @Autowired
    IEmployeeWageDao employeeWageDao;

    @Override
    public String addEmpployeeWage(EmployeeWage wage) {

        try {
            employeeWageDao.save(wage);
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }


    }
}
