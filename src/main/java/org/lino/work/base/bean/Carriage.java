package org.lino.work.base.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carriage")
public class Carriage {

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "assigned")
    @Column(length = 50)
    private String carriageId;

    @Column(length = 50)
    private String billId;

    @Column(length = 50)
    private String billName;

    @Column(length = 50)
    private String driverId;

    @Column(length = 50)
    private String phone;

    @Column(length = 50)
    private String driverName;

    private boolean isCompany;

    private Double truckFee;

    private Double frieght;

    private Double insurance;

    private Double cashPledge;

    private Double carFee;

    @Column(length = 50)
    private String state;

    private Date writeDate;

    @Column(columnDefinition = "bit(1) default 0")
    private boolean isClear;

    @Column(length = 50)
    private String send;

    @Column(length = 50)
    private String sendAdreass;

    @Column(length = 50)
    private String sendPhone;

    @Column(length = 50)
    private String reciver;

    @Column(length = 50)
    private String reciverAdreass;

    @Column(length = 50)
    private String reciverPhone;

    private Date startDate;

    private Date exceptDate;

    private Date arriveDate;

    @Column(length = 50)
    private String payType;

}
