package org.lino.work.iobus.dao;

import org.lino.work.base.bean.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface IEmployeeDao extends JpaRepository<Employee, Long> {

    public Employee findByEmployeeId(String employeeId);

    @Transactional
    @Modifying
    @Query(value = "update employee set department =?1 where department =?2", nativeQuery = true)
    public void updateDepartment(String newDepartment, String oldDepartment);

}
