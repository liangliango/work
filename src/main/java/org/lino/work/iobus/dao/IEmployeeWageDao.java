package org.lino.work.iobus.dao;

import org.lino.work.base.bean.EmployeeWage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface IEmployeeWageDao extends JpaRepository<EmployeeWage, Long> {

    @Query(value = "from employeewage  where date between  ?1 and  ?2")
    public List<EmployeeWage> findByDate(Date beginTime, Date endTime);

    public EmployeeWage findByEmployeeCode(String employeeCode);

}
