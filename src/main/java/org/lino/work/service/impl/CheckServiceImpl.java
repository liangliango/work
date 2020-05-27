package org.lino.work.service.impl;

import org.lino.work.base.bean.*;
import org.lino.work.service.ICheckService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CheckServiceImpl implements ICheckService {
    @Override
    public boolean save(ExtraIncome extraIncome) {
        return false;
    }

    @Override
    public Page<ExtraIncome> selectAllExtra(Pageable pageable) {
        return null;
    }

    @Override
    public List<ExtraIncome> selectByIncomeMonth(String incomeMonth) {
        return null;
    }

    @Override
    public List<CarReceipt> selectBySignTime(String beginTime, String endTime) {
        return null;
    }

    @Override
    public boolean save(FinanceFee financeFee) {
        return false;
    }

    @Override
    public Page<FinanceFee> selectAllFinance(Pageable pageable) {
        return null;
    }

    @Override
    public List<FinanceFee> selectByFPayoutMonth(String payoutMonth) {
        return null;
    }

    @Override
    public boolean save(ManageFee manageFee) {
        return false;
    }

    @Override
    public Page<ManageFee> selectAllManage(Pageable pageable) {
        return null;
    }

    @Override
    public List<ManageFee> selectByMPayoutMonth(String payoutMonth) {
        return null;
    }

    @Override
    public ManageFee selectByMId(int id) {
        return null;
    }

    @Override
    public boolean save(EmployeeWage employeeWage) {
        return false;
    }

    @Override
    public Page<EmployeeWage> selectAllWage(Pageable pageable) {
        return null;
    }

    @Override
    public List<EmployeeWage> selectByDate(String beginTime, String endTime) {
        return null;
    }

    @Override
    public EmployeeWage selectByEmployeeCode(String employeeCode) {
        return null;
    }

    @Override
    public List<ExtraClear> selectByBalanceDate(String beginTime, String endTime) {
        return null;
    }

    @Override
    public IncomeMonthlyTemp save() {
        return null;
    }

    @Override
    public IncomeMonthlyTemp selectAll() {
        return null;
    }

    @Override
    public IncomeMonthlyTemp selectByMonth(String month) {
        return null;
    }
}
