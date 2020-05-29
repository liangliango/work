package org.lino.work.service;

import org.lino.work.base.bean.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEmployeeService {

    public boolean save(Employee employee, int condition);

    public boolean delete(String employeeCode);

    public boolean update(Employee employee, String employeeCode, int condition);

    public Page<Employee> selectAllEmpByPage(Pageable pageable);

    public Employee selectByEmployeeCode(String employeeCode);

    List<String> findAllEmployeeId();


    boolean addEmployee(Employee employee);

    Page<Employee> findAllEmployeeByPage(Pageable pageable);

    boolean deleteEmployeeByEmployeeId(String employeeId);

    Employee findEmployeeByEmployeeId(String employeeId);

    String updateEmployeeByEmployeeId(String employeeId, Employee employee);
}
