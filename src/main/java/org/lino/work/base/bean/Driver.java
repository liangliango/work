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
 * 司机信息表
 *
 * @author lino
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "assigned")
    @Column(length = 50)
    private String driverId;

    @Column(length = 50)
    private String driverName;

    @Column(length = 18)
    private String idCard;

    @Column(length = 50)
    private String phone;

    @Column(length = 50)
    private String email;

    @Column(length = 50)
    private String gender;

    @Column(length = 50)
    private Date birthday;

    @Column(length = 50)
    private String address;

    @Column(length = 50)
    private String driveLicence;

    @Column(length = 50)
    private String carType;

    @Column(length = 50)
    private String carNo;

    @Column(length = 50)
    private double volume;

    @Column(length = 50)
    private double weight;

    @Column(length = 50)
    private String length;

    @Column(length = 50)
    private String width;

    @Column(length = 50)
    private String height;

    @Column(length = 50)
    private String remark;

    @Column(length = 50)
    private String number;

    private boolean isCompany;

}