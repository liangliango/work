package org.lino.work.base.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

/**
 * 杂费结算表
 *
 * @author lino
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "extraclear")
public class ExtraClear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String subjectName;

    @Column
    private double money;

    @Column(length = 50)
    private String remark;

    @Column
    private Date writeDate;

    @Column(length = 50)
    private String type;

}
