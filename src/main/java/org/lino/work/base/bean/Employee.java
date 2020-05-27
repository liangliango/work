package org.lino.work.base.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

/**
 * 职员信息表
 *
 * @author lino
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "assigned")
    @Column(length = 50)
    private String employeeId;

    @Column(length = 50)
    private String employeeName;

    @Column(length = 50)
    private String department;

    @Column(length = 50)
    private String position;

    @Column(length = 50)
    private String gender;

    @Column(length = 50)
    private Date birthday;

    @Column(length = 50)
    private String idcard;

    @Column(length = 50)
    private String phone;

}
