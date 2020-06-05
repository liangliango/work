package org.lino.work.busi.controller;

import org.lino.work.base.bean.EmployeeWage;
import org.lino.work.base.bean.Income;
import org.lino.work.base.bean.Upd;
import org.lino.work.service.IEmployeeService;
import org.lino.work.service.IEmployeeWageService;
import org.lino.work.service.IInComeService;
import org.lino.work.service.IUpdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping(value = "/income")
@ControllerAdvice
@RestController
public class IncomeController {

    @Autowired
    IInComeService inComeService;

    @Autowired
    IEmployeeService employeeService;

    @Autowired
    IEmployeeWageService employeeWageService;

    @Autowired
    IUpdService updService;

    @RequestMapping(value = "/countIncomeByMonth",method = RequestMethod.GET)
    public Income countIncomeByMonth(){

        return inComeService.countIncomeByMonth();
    }

    @RequestMapping(value = "/findAllEmployeeId",method = RequestMethod.GET)
    public List<String> findAllEmployeeId(){

        return employeeService.findAllEmployeeId();
    }

    @RequestMapping(value = "/addEmpployeeWage",method = RequestMethod.POST)
    public String addEmpployeeWage(EmployeeWage wage){

        return employeeWageService.addEmpployeeWage(wage);
    }

    @RequestMapping("/upd")
    public String upd(Upd upd){

        return updService.upd(upd);
    }



}
