package org.lino.work.service.impl;

import org.lino.work.base.bean.Employee;
import org.lino.work.iobus.dao.IEmployeeDao;
import org.lino.work.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("IEmployeeService")
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    IEmployeeDao employeeDao;
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

    @Override
    public List<String> findAllEmployeeId() {
        List<Employee> list = employeeDao.findAll();
        List<String> list1 = new ArrayList<>();

        for (Employee temp :list) {
            list1.add(temp.getEmployeeId());
        }
        return list1;
    }

    @Transactional
    @Override
    public boolean addEmployee(Employee employee) {

        try {
            String name = "YG"+System.currentTimeMillis();
            employee.setEmployeeId(name);
            employeeDao.save(employee);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Page<Employee> findAllEmployeeByPage(Pageable pageable) {
        return employeeDao.findAll(pageable);
    }

    @Override
    public boolean deleteEmployeeByEmployeeId(String employeeId) {

        try {
            System.out.println(employeeId);
            Employee employee = employeeDao.findByEmployeeId(employeeId);
            employeeDao.delete(employee);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Employee findEmployeeByEmployeeId(String employeeId) {
        return employeeDao.findByEmployeeId(employeeId);
    }

    @Transactional
    @Override
    public String updateEmployeeByEmployeeId(String employeeId, Employee employee) {

        try {
            employeeDao.findByEmployeeId(employeeId);
            employee.setEmployeeId(employeeId);
            employeeDao.save(employee);
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}
