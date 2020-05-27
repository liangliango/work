package org.lino.work.service;

import org.lino.work.base.bean.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEmployeeService {

    public boolean save(Employee employee, int condition);

    public boolean delete(String employeeCode);

    public boolean update(Employee employee, String employeeCode, int condition);

    public Page<Employee> selectAllEmpByPage(Pageable pageable);

    public Employee selectByEmployeeCode(String employeeCode);

}
