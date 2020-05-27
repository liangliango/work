package org.lino.work.service.impl;

import org.lino.work.base.bean.Employee;
import org.lino.work.service.IEmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class EmployeeServiceImpl implements IEmployeeService {
    @Override
    public boolean save(Employee employee, int condition) {
        return false;
    }

    @Override
    public boolean delete(String employeeCode) {
        return false;
    }

    @Override
    public boolean update(Employee employee, String employeeCode, int condition) {
        return false;
    }

    @Override
    public Page<Employee> selectAllEmpByPage(Pageable pageable) {
        return null;
    }

    @Override
    public Employee selectByEmployeeCode(String employeeCode) {
        return null;
    }
}
