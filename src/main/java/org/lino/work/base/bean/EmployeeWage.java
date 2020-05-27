package org.lino.work.base.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

/**
 * 员工工资表
 *
 * @author lino
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "employeewage")
public class EmployeeWage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String employeeId;

    private double basicWage;

    private double performanceWage;

    private double extraWage;

    private Date date;

}
