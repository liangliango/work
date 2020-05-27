package org.lino.work.base.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 职员用户关联
 *
 * @author lino
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employeeuser")
public class EmployeeUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int employeeId;// 职员ID

    private int userId;// 用户ID


}
