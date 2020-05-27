package org.lino.work.base.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 司机结算主表
 *
 * @author lino
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "driverclear")
public class DriverClear {

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "assigned")
    @Column(length = 50)
    private String id;

    @Column(length = 50)
    private String driverId;

    @Column(length = 50)
    private String carriageId;

    private Double truckFee;

    private Double frieght;

    private Double insurance;

    private Double cashPledge;

    private Double carFee;

    private Double money;

    private Double err;

    private Date writeDate;

    private Boolean isClear;

    private Date clearDate;
}
